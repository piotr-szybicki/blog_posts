package com.example.azureb2c.security;

import com.microsoft.azure.spring.autoconfigure.aad.AADAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AADAuthenticationFilter aadAuthFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//allow all request access this url
		http.authorizeRequests().antMatchers("/home").permitAll();

		//access to this url requires authentication
		http.authorizeRequests().antMatchers("/api/**").authenticated();

		http.authorizeRequests().anyRequest().permitAll();

		//set up the csfr token
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

}
