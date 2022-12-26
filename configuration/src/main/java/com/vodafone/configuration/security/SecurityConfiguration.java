package com.vodafone.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("user"))
                .roles("END_USER")
                .and()
                .withUser("sales_person")
                .password(encoder.encode("sales_person"))
                .roles("SALES_PERSON");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/dcs/addTrackingDevice").hasRole("END_USER")
                .antMatchers("/dcs/updateTrackingDevice").hasRole("END_USER")
                .antMatchers("/dcs/deleteTrackingDevice/{deviceID}").hasRole("END_USER")
                .antMatchers("dcs/activateTrackingDevice/{deviceID}").hasRole("END_USER")
                .antMatchers("/dcs/sellTrackingDevice/{deviceID}").hasRole("SALES_PERSON")
                .antMatchers("/welcome").permitAll()
                .and()
                .formLogin()
                .and()
                .logout().logoutUrl("/auth_user/logout");
    }
}
