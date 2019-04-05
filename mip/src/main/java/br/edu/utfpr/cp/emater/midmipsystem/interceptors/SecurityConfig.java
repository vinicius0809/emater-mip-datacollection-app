package br.edu.utfpr.cp.emater.midmipsystem.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").authenticated()
                .and().formLogin().permitAll().and().logout().permitAll();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}teste").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("tech").password("{noop}teste").roles("TECH");
        auth.inMemoryAuthentication().withUser("super").password("{noop}teste").roles("SUPERVISOR");
        auth.inMemoryAuthentication().withUser("user").password("{noop}teste").roles("USER");
    }
}
