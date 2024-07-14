	package com.webapp.codeathon.config;
	
	import java.util.Arrays;
	import java.util.Collections;
	
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.http.HttpMethod;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.http.SessionCreationPolicy;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;
	import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
	import org.springframework.web.cors.CorsConfiguration;
	import org.springframework.web.cors.CorsConfigurationSource;
	import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
	
	import jakarta.servlet.http.HttpServletRequest;
	
	@Configuration
	@EnableWebSecurity
	public class AppConfig {
		
		 @Bean
		    public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {
			 System.out.println("ENTERED IN SECURITY FILTER CHAIN");
		        http
		            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		            .authorizeHttpRequests(auth -> auth
		                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
		                .requestMatchers(HttpMethod.GET, "/api/auth/**").permitAll()
		                .requestMatchers(HttpMethod.POST, "/subadmin/**").permitAll()
		                .requestMatchers(HttpMethod.GET, "/subadmin/**").permitAll()
		                .requestMatchers(HttpMethod.POST, "/contestant/**").permitAll()
		                .requestMatchers(HttpMethod.GET, "fetch-jwt-auth/token").permitAll()
		                .requestMatchers("/authenticatetoken").permitAll()
		                .anyRequest().authenticated()
		            )
		            .addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class)
		            .csrf(csrf -> csrf.disable())
		            .cors(cors -> cors.configurationSource(corsConfigurationSource()));
	
		        return http.build();
		    }
	
		    @Bean
		    public CorsConfigurationSource corsConfigurationSource() {
		        CorsConfiguration configuration = new CorsConfiguration();
		        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		        configuration.setAllowCredentials(true);
		        configuration.setExposedHeaders(Arrays.asList("Authorization"));
	
		        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		        source.registerCorsConfiguration("/**", configuration);
		        return source;
		    }
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}
