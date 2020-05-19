package com.coodcool.icook.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").authenticated()
                .antMatchers(HttpMethod.GET, "/recipe/{id}/comments").permitAll()
                .antMatchers(HttpMethod.POST, "/recipe/{id}/comments").authenticated()
                .antMatchers(HttpMethod.GET, "/favorites").authenticated()
                .antMatchers(HttpMethod.POST, "/favorites").authenticated()
                .antMatchers(HttpMethod.DELETE, "/favorites/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/recipe/{id}/personal-note").authenticated()
                .antMatchers(HttpMethod.POST, "/recipe/{id}/personal-note").authenticated();
    }
}
