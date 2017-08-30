package com.getjavajob.project.pyltsin.project.config;

import com.getjavajob.project.pyltsin.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Pyltsin on 26.08.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/index")
                .failureUrl("/index?error=true")
                .defaultSuccessUrl("/account", true)
                .and()
                .rememberMe()
                .tokenValiditySeconds(60 * 60 * 2)
                .and()
                .logout()
                .logoutSuccessUrl("/index")
                .logoutUrl("/signout")
                .and()
                .authorizeRequests()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/js/*").permitAll()
                .antMatchers("/register.html").permitAll()
                .antMatchers("/RegisterServlet").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
