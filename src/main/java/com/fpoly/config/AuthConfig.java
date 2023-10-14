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
import org.springframework.security.web.SecurityFilterChain;

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
				sessionService.setSession("user", userInfo);
				return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
			}
		};
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/admin/**").hasRole("Admin")
				.and().authorizeHttpRequests().requestMatchers("/cart/**","/checkout/**").authenticated().and()
				.authorizeHttpRequests().anyRequest().permitAll().and().exceptionHandling()
				.accessDeniedPage("/auth/access/denied").and().formLogin().loginPage("/auth/login_form")
				.loginProcessingUrl("/login").defaultSuccessUrl("/auth/login/success", true)
				.and().logout().logoutUrl("/logoff").logoutSuccessUrl("/auth/logoff/success").and().build();
	}
	

}
