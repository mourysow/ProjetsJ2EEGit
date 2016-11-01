package com.websystique.springmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("myDBAuthenticationService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		  // The pages does not require login
       // http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();
		
        // The pages does not require login
       // http.authorizeRequests().antMatchers("/", "/test", "/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')");
        
        
		//http.authorizeRequests().antMatchers("/edit-user-*").access("hasRole('ADMIN') or hasRole('DBA')");
       
	    http.authorizeRequests().antMatchers("/appi/employe/**").permitAll();
      
        http.authorizeRequests()
				.antMatchers("/api/users/", "/test", "/api/users/list")
				.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
				.antMatchers("/api/users/newuser/**", "/api/users/delete-user-*")
				.access("hasRole('ADMIN')")
				.antMatchers("/api/users/edit-user-*")
				.access("hasRole('ADMIN') or hasRole('DBA')")
				    .and().formLogin()
				.loginPage("/api/users/login").loginProcessingUrl("/api/users/login")
				.usernameParameter("ssoId").passwordParameter("password").and()
				.rememberMe().rememberMeParameter("remember-me")
				.tokenRepository(tokenRepository).tokenValiditySeconds(86400)
				.and().csrf().and().exceptionHandling()
				.accessDeniedPage("/api/users/Access_Denied");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

}
