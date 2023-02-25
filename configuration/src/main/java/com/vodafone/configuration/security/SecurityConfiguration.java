package com.vodafone.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .antMatchers(
                        "/dcs/addTrackingDevice",
                        "/dcs/updateTrackingDevice",
                        "/dcs/deleteTrackingDevice/{deviceID}",
                        "dcs/activateTrackingDevice/{deviceID}",
                        "/dcs/sellTrackingDevice/{deviceID}",
                        "security"
                ).authenticated()
                .antMatchers("/welcome").permitAll()
                .and().formLogin().and().httpBasic();

        return httpSecurity.build();
    }

    /**
     * This method returns the saved the credentials into the in-memory of spring boot.
     * @return InMemoryUserDetailsManager
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        /* This is the 1st non-productive approach */
        /*UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("1234").authorities("admin").build();

        UserDetails user =
                User.withDefaultPasswordEncoder().username("user").password("4321").authorities("read").build();

        return new InMemoryUserDetailsManager(admin, user);*/


        /* This is the 2nd non-productive approach */
        UserDetails admin = User.withUsername("admin").password("1234").authorities("admin").build();

        UserDetails user =  User.withUsername("user").password("4321").authorities("read").build();


        return new InMemoryUserDetailsManager(admin, user);
    }
}
