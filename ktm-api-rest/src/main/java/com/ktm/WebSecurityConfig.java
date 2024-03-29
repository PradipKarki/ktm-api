package com.ktm;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/swagger*").permitAll(); // $NON-NLS-1$
    http.authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .and() //$NON-NLS-1$
        .authorizeRequests()
        .antMatchers("/h2/**")
        .permitAll(); //$NON-NLS-1$
    // todo remove CSRF disable in production
    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
