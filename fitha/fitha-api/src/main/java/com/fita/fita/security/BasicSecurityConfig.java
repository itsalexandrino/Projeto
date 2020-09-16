package com.fita.fita.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

	@EnableWebSecurity
	public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.antMatchers(HttpMethod.GET, "/produtos/**").permitAll()
		.antMatchers("/produtos/{id}").permitAll()
		.antMatchers(HttpMethod.GET, "/categoria").permitAll()
		.antMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/usuario/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/produtos/cadastrar").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/categoria/cadastrar").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/produtos").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/usuarios").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/categorias").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/produtos/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/categoria/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/usuarios/*").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
}