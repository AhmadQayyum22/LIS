package com.perficient.Lab.Information.System.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class LISSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/LIS/addorupdateOrderItem").ignoringAntMatchers("/api/**").ignoringAntMatchers("/LIS/deleteOrderItem/**").and()
                .authorizeRequests()
                .mvcMatchers("/LIS/login").permitAll()
                .mvcMatchers("/LIS/user/**").permitAll()
                .mvcMatchers("/LIS/**").authenticated()
                .mvcMatchers("/api/**").authenticated()
                .and().formLogin().loginPage("/LIS/login")
                .defaultSuccessUrl("/LIS/allPatient").failureUrl("/LIS/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/LIS/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();
    }

}
