package com.mk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

		return security.authorizeHttpRequests(registry -> {
			registry.requestMatchers("/home").permitAll();
			registry.requestMatchers("/admin/**").hasRole("ADMIN");
			registry.requestMatchers("/user/**").hasRole("USER");
			registry.anyRequest().authenticated();

		}).formLogin(AbstractAuthenticationFilterConfigurer::permitAll).build();
	}

	@Bean
	public UserDetailsService detailsService() {
		UserDetails userDetails = User.builder().username("murali")
				.password("$2a$12$FYkOA01HrjAHGRVFvMAXeuzpux7txxJ424bhVJ5/Y72Apt24XfOWG").roles("USER").build();

		UserDetails adminDetails = User.builder().username("kolli")
				.password("$2a$12$czD8xnG/GrC.f6Rnjq3TzOs3CfN6TBrj4em4gRc9xG5TfRXefNKiC").roles("ADMIN", "USER")
				.build();

		return new InMemoryUserDetailsManager(userDetails, adminDetails);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}