package br.edu.utfpr.cp.emater.midmipsystem.interceptors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class Interceptor implements HandlerInterceptor {

    private static final String ADMIN = "ROLE_ADMIN";
    private static final String TECH = "ROLE_TECH";
    private static final String SUPERVISOR = "ROLE_SUPERVISOR";
    private static final String USER = "ROLE_USER";

    private static int httpCode = 200;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated())
            return false;

        var roles = authentication.getAuthorities();
        var relativeUrl = request.getServletPath();


        List<String> requiredRoles;
        List<String> urls;

        urls = Arrays.asList("/region", "/macroregion", "/supervisor");
        if (urls.contains(relativeUrl)) {
            requiredRoles = Arrays.asList(ADMIN);
            for (var role : roles) {
                if(!requiredRoles.contains(role.toString())){
                    httpCode = 400;
                    return false;
                }

            }
        }

        urls = Arrays.asList("/field", "/farmer");
        if (urls.contains(relativeUrl)) {
            requiredRoles = Arrays.asList(ADMIN, SUPERVISOR);
            for (var role : roles) {
                if(!requiredRoles.contains(role.toString())){
                    httpCode = 400;
                    return false;
                }
            }
        }

        urls = Arrays.asList("/harvest", "/survey-field", "/pest-survey", "/pest");
        if (urls.contains(relativeUrl)) {
            requiredRoles = Arrays.asList(ADMIN, SUPERVISOR, TECH);
            for (var role : roles) {
                if(!requiredRoles.contains(role.toString())){
                    httpCode = 400;
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if(httpCode == 400){
            // redireciona para pagina de erro 400
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
    }

}