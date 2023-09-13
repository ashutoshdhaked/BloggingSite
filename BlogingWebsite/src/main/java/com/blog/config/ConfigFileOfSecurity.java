package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.blog.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class ConfigFileOfSecurity {

	@Bean
    public UserDetailsService getUserDaetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder paaswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDaetailsService());
		daoAuthenticationProvider.setPasswordEncoder(this.paaswordEncoder());
        return daoAuthenticationProvider;		
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests((authz) -> authz
	            .requestMatchers("/admin/**").hasRole("AdminUser")
	        //    .requestMatchers("/user/**").hasRole("NormalUser")
	            .requestMatchers("/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(formLogin -> formLogin
	            .loginPage("/loginsineup") 
	            .permitAll() 
	        )
	        .authenticationProvider(authenticationProvider());

	    return http.build();
	}
	
	
	
}
