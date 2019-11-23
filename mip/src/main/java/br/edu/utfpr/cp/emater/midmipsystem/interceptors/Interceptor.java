package br.edu.utfpr.cp.emater.midmipsystem.interceptors;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.*;
import br.edu.utfpr.cp.emater.midmipsystem.domain.mip.MipPestSurvey;
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
    @Autowired
    private RegionRepository regionRepository;

    private static int httpCode = 200;
    private boolean verifyUserSession = false;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        verifyUserSession = false;
        var authentication = SecurityContextHolder.getContext().getAuthentication();

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
            var roleName = role.toString().split("_")[1];

            if (roleName.equalsIgnoreCase("ADMIN")) {
                grants = roleRepository.findById((long) 1).get().getGrants(domain);
            } else if (roleName.equalsIgnoreCase("SUPERVISOR")) {
                grants = roleRepository.findById((long) 2).get().getGrants(domain);
            } else if (roleName.equalsIgnoreCase("FARMER")) {
                grants = roleRepository.findById((long) 3).get().getGrants(domain);
            } else grants = null;

            if (grants != null && verifyAuthority(grants, action))
                return true;
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
            action = "/" + dividedUrl[2];
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
        return Arrays.asList("region", "macroregion", "field", "supervisor", "user", "farmer", "pest-survey");
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && !isRedirectView(modelAndView) && this.verifyUserSession) {
            var user = SecurityContextHolder.getContext().getAuthentication().getName();
            modelAndView = changeModelAndView(modelAndView, user, request.getServletPath());
        }
    }

    private ModelAndView changeModelAndView(ModelAndView modelAndView, String login, String urlPath) {
        var domain = getUrlModelName(urlPath);

        if (!getDomains().contains(domain) && !domain.equals("mipPestSurvey"))
            return modelAndView;

        var modelList = (ArrayList<Object>) modelAndView.getModel().get(domain + "s");

        User user = findByLogin(login);

        var x = modelList.iterator();

        while (x.hasNext()){
            var item = x.next();
            if (!validateUserDomain(item, user)) {
                x.remove();
            }
        }

        modelAndView.getModel().remove(domain);
        modelAndView.getModel().put(domain, modelList);

        return modelAndView;
    }


    private boolean validateUserDomain(Object item, User user) {
        if (item instanceof Field)
            return validateUserDomain((Field) item, user);

        else if (item instanceof Region)
            return validateUserDomain((Region) item, user);

        else if (item instanceof MacroRegion)
            return validateUserDomain((MacroRegion) item, user);

        else if (item instanceof Farmer)
            return validateUserDomain((Farmer) item, user);

        else if (item instanceof MipPestSurvey)
            return validateUserDomain((MipPestSurvey) item, user);

        else if (item instanceof Supervisor)
            return validateUserDomain((Supervisor) item, user);

        else if (item instanceof User)
            return validateUserDomain((User) item, user);

        return false;
    }

    private boolean validateUserDomain(Field item, User user) {
        if (user.getSupervisor() == null) {
            if (item.getFarmer().getId() == user.getFarmer().getId()) {
                return true;
            }
        } else {
            for (var supervisor : item.getSupervisors()) {
                if (supervisor.getId() == user.getSupervisor().getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validateUserDomain(Region item, User user) {
        if(user.getSupervisor() == null){
            var field = findFieldByFarmer(user.getFarmer());
            var city = field.getCity();
            var region = findRegionByCity(city);

            if(item.getId() == region.getId()){
                return true;
            }
        }
        else if (item.getId() == user.getSupervisor().getRegion().getId()) {
            return true;
        }
        return false;
    }

    private boolean validateUserDomain(MacroRegion item, User user) {
        if (user.getSupervisor() == null) {
            var field = findFieldByFarmer(user.getFarmer());
            var city = field.getCity();
            var region = findRegionByCity(city);

            if (item.getId() == region.getMacroRegion().getId()) {
                return true;
            }

        } else if (item.getId() == user.getSupervisor().getRegion().getMacroRegion().getId()) {
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

    private boolean validateUserDomain(User item, User user) {
        if (item.getId() == user.getId()) {
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
        if (user.getSupervisor() == null) {
            if (farmer.getId() == user.getFarmer().getId()) {
                return farmer;
            }
        } else {
            for (var field : fieldRepository.findAll()) {
                for (var supervisor : field.getSupervisors()) {
                    if (supervisor.getId() == user.getSupervisor().getId()) {
                        if (farmer.getId() == field.getFarmer().getId()) {
                            return farmer;
                        }
                    }
                }
            }
        }
        return null;
    }

    private Field findFieldByFarmer(Farmer farmer) {
        for (var field : fieldRepository.findAll()) {
            if (field.getFarmer().getId() == farmer.getId()) {
                return field;
            }
        }

        return null;
    }

    private Region findRegionByCity(City city) {
        for (var region : regionRepository.findAll()) {
            for (var regionCity : region.getCities()) {
                if (city.getId() == regionCity.getId()) {
                    return region;
                }
            }
        }

        return null;
    }

    // Métodos do postHandle()
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