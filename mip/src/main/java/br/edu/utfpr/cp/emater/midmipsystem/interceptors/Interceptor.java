package br.edu.utfpr.cp.emater.midmipsystem.interceptors;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.*;
import br.edu.utfpr.cp.emater.midmipsystem.domain.mip.MipPestSurvey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private Environment env;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FieldRepository fieldRepository;

    private static Logger log = LoggerFactory.getLogger(Interceptor.class);
    private static int httpCode = 200;
    private boolean verifyUserSession = false;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!isAuthenticated(authentication))
            return false;

        var roles = authentication.getAuthorities();
        var relativeUrl = request.getServletPath();

        if (verifyViewUrl(roles, relativeUrl))
            return true;

        else {
            httpCode = 400;
            return false;
        }
    }

    private boolean verifyViewUrl(Collection<? extends GrantedAuthority> roles, String urlPath) {

        var domainAction = getUrlDomainAction(urlPath).split("/");
        var domain = domainAction[0];


        if (!getDomains().contains(domain))
            return true;

        var action = domainAction[1];

        for (var role : roles) {
            String grants;
            switch (role.toString().split("_")[1]) {
                case "ADMIN":

                    grants = roleRepository.findById((long) 1).get().getGrants(domain);
                    if (verifyAuthority(grants, action))
                        return true;

                case "SUPERVISOR":

                    grants = roleRepository.findById((long) 2).get().getGrants(domain);
                    if (verifyAuthority(grants, action))
                        return true;

                case "FARMER":

                    grants = roleRepository.findById((long) 3).get().getGrants(domain);
                    if (verifyAuthority(grants, action))
                        return true;

                default:
                    return false;
            }
        }

        return false;
    }

    private boolean verifyAuthority(String grants, String action) {

        var grantsCreate = grants.split("-")[0];
        var grantsRead = grants.split("-")[1];
        var grantsUpdate = grants.split("-")[2];
        var grantsDelete = grants.split("-")[3];

        action = "/" + action;

        if (action.equals(env.getProperty("app.view.route.read"))) {
            switch (grantsRead) {
                case "R":
                    return true;

                case "r":
                    this.verifyUserSession = true;
                    return true;

                default:
                    return false;
            }

        } else if (action.equals(env.getProperty("app.view.route.create"))) {
            return !grantsCreate.equals("x");

        } else if (action.equals(env.getProperty("app.view.route.update"))) {
            return !grantsUpdate.equals("x");

        } else {
            return !grantsDelete.equals("x");
        }
    }

    private String getUrlDomainAction(String urlPath) {

        var dividedUrl = urlPath.split("/");
        var domain = "";
        var action = env.getProperty("app.view.route.read");

        if (dividedUrl.length > 1) {
            domain = dividedUrl[1];
        }

        if (dividedUrl.length > 2) {
            action = dividedUrl[2];
        }

        return domain + action;
    }

    private String getUrlModelName(String urlPath) {

        var dividedUrl = urlPath.split("/");
        var domain = "";
        if (dividedUrl.length > 1) {
            domain = dividedUrl[1];
        }

        if (domain.equalsIgnoreCase("city")) {
            domain = "citie";
        } else if (domain.equalsIgnoreCase("pest-survey")) {
            domain = "mipPestSurvey";
        }

        return domain;
    }

    private List<String> getDomains() {
        // return Arrays.asList("city", "region", "macroregion", "field", "harvest", "pest", "supervisor", "users", "farmer", "survey-field", "pest-survey");
        return Arrays.asList("region", "macroregion", "field", "supervisor", "users", "farmer", "pest-survey");
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser");
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (httpCode == 400) {
            // redireciona para pagina de erro 400
            response.sendRedirect("/erro");
        }

        if (modelAndView != null && !isRedirectView(modelAndView) && this.verifyUserSession) {
            addToModelUserDetails(modelAndView);
            var user = SecurityContextHolder.getContext().getAuthentication().getName();
            modelAndView = changeModelAndView(modelAndView, user, request.getServletPath());
        }
    }

    private ModelAndView changeModelAndView(ModelAndView modelAndView, String login, String urlPath) {
        var domain = getUrlModelName(urlPath);

        if (!getDomains().contains(domain) && !domain.equals("mipPestSurvey"))
            return modelAndView;

        var modelList = (List<Object>) modelAndView.getModel().get(domain + "s");

        User user = findByLogin(login);

        var removeFromModel = new ArrayList<>();

        for (var item : modelList) {
            if (!validateUserDomain(item, user)) {
                removeFromModel.add(item);
            }
        }

        for (var region : removeFromModel) {
            modelList.remove(region);
        }

        modelAndView.getModel().remove(domain);
        modelAndView.getModel().put(domain, modelList);

        return modelAndView;
    }


    private boolean validateUserDomain(Object item, User user) {
        if (item instanceof Field)
            return validateUserDomain((Field) item, user);

        else if (item instanceof Region)
            return validateUserDomain((Region)item, user);

        else if (item instanceof MacroRegion)
            return validateUserDomain((MacroRegion) item, user);

        else if (item instanceof Farmer)
            return validateUserDomain((Farmer) item, user);

        else if (item instanceof MipPestSurvey)
            return validateUserDomain((MipPestSurvey) item, user);

        else if (item instanceof Supervisor)
            return validateUserDomain((MipPestSurvey) item, user);

        return false;
    }

    private boolean validateUserDomain(Field item, User user) {
        for (var supervisor : item.getSupervisors())
            if (supervisor.getId() == user.getSupervisor().getId()) {
                return true;
            }
        return false;
    }

    private boolean validateUserDomain(Region item, User user) {
        if (item.getId() == user.getSupervisor().getRegion().getId()) {
            return true;
        }
        return false;
    }


    private boolean validateUserDomain(MacroRegion item, User user) {
        if (item.getId() == user.getSupervisor().getRegion().getMacroRegion().getId()) {
            return true;
        }
        return false;
    }

    private boolean validateUserDomain(Farmer item, User user) {
        if (findFarmerByUser(item, user) != null) {
            return true;
        }
        return false;
    }

    private boolean validateUserDomain(MipPestSurvey item, User user) {
        for (var supervisor : item.getSurveyField().getField().getSupervisors()) {
            if (supervisor.getId() == user.getSupervisor().getId()) {
                return true;
            }
        }
        return false;
    }

    private boolean validateUserDomain(Supervisor item, User user) {
        if (item.getId() == user.getSupervisor().getId()) {
            return true;
        }
        return false;
    }

    private User findByLogin(String login) {
        for (var user : userRepository.findAll()) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    private Farmer findFarmerByUser(Farmer farmer, User user) {
        for (var field : fieldRepository.findAll()) {
            for (var supervisor : field.getSupervisors()) {
                if (supervisor.getId() == user.getSupervisor().getId()) {
                    if (farmer.getId() == field.getFarmer().getId()) {
                        return farmer;
                    }
                }
            }
        }
        return null;
    }

    // Métodos do postHandle()
    private void addToModelUserDetails(ModelAndView model) {
        log.info("=============== addToModelUserDetails =========================");
        String loggedUsername = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        model.addObject("loggedUsername", loggedUsername);
        log.trace("session : " + model.getModel());
        log.info("=============== addToModelUserDetails =========================");
    }

    public static boolean isRedirectView(ModelAndView mv) {
        String viewName = mv.getViewName();
        if (viewName.startsWith("redirect:/")) {
            return true;
        }
        View view = mv.getView();
        return (view != null && view instanceof SmartView
                && ((SmartView) view).isRedirectView());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
    }

}