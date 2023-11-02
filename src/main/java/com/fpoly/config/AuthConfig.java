package com.fpoly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fpoly.model.Account;
import com.fpoly.service.AccountService;
import com.fpoly.service.SessionService;

@Configuration
@EnableWebSecurity
public class AuthConfig {
	@Autowired
	AccountService accountService;
	@Autowired
	SessionService sessionService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	// authentication
	public UserDetailsService userDetailsService(PasswordEncoder pe) {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Account userInfo = accountService.findByid(username);
				String password = userInfo.getPassword();
				String roles = userInfo.getRole().getRoleName();
				Boolean active = userInfo.getActive();
				sessionService.setSession("user", userInfo, 300);
				return User.withUsername(username).password(pe.encode(password)).roles(roles).accountExpired(!active)
						.build();
			}
		};
	}
	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
	    return new CustomAuthenticationFailureHandler();
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http.csrf().disable()
	            .authorizeHttpRequests()
	                .requestMatchers("/admin/**").hasRole("Admin")
	                .and()
	                .authorizeHttpRequests()
	                .requestMatchers("/cart/**", "/checkout/**").authenticated()
	                .and()
	                .authorizeHttpRequests()
	                .anyRequest().permitAll()
	                .and()
	            .exceptionHandling()
	                .accessDeniedPage("/auth/access/denied")
	                .and()
	            .formLogin()
	                .loginPage("/auth/login")
	                .loginProcessingUrl("/login")
	                .defaultSuccessUrl("/auth/login/success", true)
	                .failureHandler(customAuthenticationFailureHandler())
	                .and()
	            .logout()
	                .logoutUrl("/logoff")
	                .logoutSuccessUrl("/auth/logoff/success")
	                .and()
	            .build();
	}


}
