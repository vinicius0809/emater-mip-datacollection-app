package br.edu.utfpr.cp.emater.midmipsystem.interceptors;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private Environment env;
    @Autowired
    private RoleRepository roleRepository;

    private static int httpCode = 200;

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
            return !grantsRead.equals("x");

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

    private List<String> getDomains() {
        return Arrays.asList("city", "region", "macroregion", "field", "harvest", "pest", "supervisor", "users", "farmer", "survey-field", "pest-survey");
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
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
    }

}