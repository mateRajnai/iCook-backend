package com.coodcool.icook.security;

import com.coodcool.icook.dao.implementation.JwtTokenBlackListDaoMem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;
    private final JwtTokenBlackListDaoMem blacklist;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

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
                .antMatchers("/logout").permitAll()
                .antMatchers(HttpMethod.GET, "/recipe/{id}/comments").permitAll()
                .antMatchers(HttpMethod.POST, "/recipe/{id}/comments").authenticated()
                .antMatchers(HttpMethod.GET, "/favorites").authenticated()
                .antMatchers(HttpMethod.POST, "/favorites").authenticated()
                .antMatchers(HttpMethod.DELETE, "/favorites/{id}").authenticated()
                .antMatchers(HttpMethod.GET, "/recipe/{id}/personal-note").authenticated()
                .antMatchers(HttpMethod.POST, "/recipe/{id}/personal-note").authenticated()
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenFilterForBlackList(jwtTokenServices, blacklist), UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .addLogoutHandler(new JwtDoBlackListing(jwtTokenServices, blacklist))
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                .and().cors()
                .and().headers().httpStrictTransportSecurity().disable();
    }
}