package fr.oury.sow.securite;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
		                                                                                      /*
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN", "PROF");
		auth.inMemoryAuthentication().withUser("prof1").password("123").roles("PROF");
		auth.inMemoryAuthentication().withUser("sco1").password("123").roles("SCOLARITE");
		auth.inMemoryAuthentication().withUser("et1").password("123").roles("ETUDIANT");
		                                                                                      */
		auth.jdbcAuthentication()
		  .dataSource(dataSource)
		  .usersByUsernameQuery("select username as Principal, password as credentiales, true from users where username = ?")
		  .authoritiesByUsernameQuery("select user_username as Principal, roles_role as role from users_roles where user_username=?")
		  .rolePrefix("ROLE_");
		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// autentifier toutes les ressources
		http
		  .csrf().disable()
		  .authorizeRequests()
		     .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
		     .anyRequest()
		       .authenticated()
		         .and()
		  .formLogin()
		     .loginPage("/login")
		       .permitAll()
		  .defaultSuccessUrl("/index.html")
		    .and()
		  .logout()
			 .invalidateHttpSession(true)
			 .logoutUrl("/logout")
		     .permitAll();
	}

}
