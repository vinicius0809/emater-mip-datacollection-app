package br.edu.utfpr.cp.emater.midmipsystem.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class PestInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    PestInterceptor pestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pestInterceptor);
    }
}