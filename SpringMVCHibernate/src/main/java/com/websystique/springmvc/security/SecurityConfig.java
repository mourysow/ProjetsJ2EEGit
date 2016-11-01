package com.websystique.springmvc.security;

import javax.sql.DataSource;

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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration// @conf annotation de la class chargee de configurer la secu
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("myDBAuthenticationService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(
				"select username as Principal, password as credentiales, true from users where username = ?")
		.authoritiesByUsernameQuery(
				"select user_username as Principal, roles_role as role from users_roles where user_username=?")
		.rolePrefix("ROLE_");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/appi/employe/**").permitAll();

		http.authorizeRequests()
				.antMatchers("/list")
				  .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")

				.antMatchers("/newuser/**", "/delete-user-*")
				  .access("hasRole('ADMIN')")

				.antMatchers("/edit-user-*")
				  .access("hasRole('ADMIN') or hasRole('DBA')")

				.and()
				   .formLogin()
					   .loginPage("/login")
					   .loginProcessingUrl("/login")
					   .usernameParameter("ssoId")
				       .passwordParameter("password")

				.and()
				  .rememberMe()
				    .rememberMeParameter("remember-me")
				    .tokenRepository(tokenRepository)
				    .tokenValiditySeconds(86400)

				.and()
				   .csrf()
				 
				 .and()
				   .exceptionHandling()
				     .accessDeniedPage("/Access_Denied");
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
	
	
	public void addViewControllers(ViewControllerRegistry registry){
		
		registry.addViewController("/loginn").setViewName("loginn");
		registry.addViewController("/logoutt").setViewName("logint");
		
	}

}
