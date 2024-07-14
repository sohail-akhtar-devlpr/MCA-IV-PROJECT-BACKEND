package com.webapp.codeathon.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfiguration {
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean 
//	public UserDetailsService userDetailsService() {
//		UserDetails normalUser= User
//								.withUsername("sohail")
//								.password(passwordEncoder().encode("sohail"))
//								.roles("NORMAL")
//								.build();
//		
//		UserDetails adminlUser= User
//				.withUsername("sohail akhtar")
//				.password(passwordEncoder().encode("sohailakhtar"))
//				.roles("ROLE_NORMAL")
//				.build();
//		
//		InMemoryUserDetailsManager inMemoryUserDetailsManager= new InMemoryUserDetailsManager(normalUser,adminlUser);
//				return inMemoryUserDetailsManager;
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 1. All requests should be authenticated.
		http.authorizeHttpRequests(
				auth->
				auth.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
				.requestMatchers("/authenticatetoken").permitAll()
				.anyRequest().authenticated()
				);
		// 2. if a request is not authenticated, a web page is shown
		http.httpBasic(withDefaults());
		http.csrf( (csrf) -> csrf.disable() );
		return http.build();
	}
}
