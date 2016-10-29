package org.gestion.bp.ath0.provider;

import lombok.extern.slf4j.Slf4j;

import org.gestion.bp.dto.user.User;
import org.gestion.bp.model.UserRole;
import org.gestion.bp.service.IFoLoginService;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Prodiver d'authentification
 * 
 * @author JEANFRG
 * 
 */
@Slf4j
public class FrontAuthentificationProvider implements AuthenticationProvider {

	/**
	 * Service d'authentificaiton
	 */
	private IFoLoginService	loginService;

	@Value("${default.login}")
	private String			default_login;
	@Value("${default.secret}")
	private String			default_password;

	public void setLoginService(IFoLoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//log.trace("INFO ; {} ; authenticate ;");
		try {

			String uuid = ((User) authentication.getPrincipal()).getUuid();
			String userLogin = ((User) authentication.getPrincipal()).getUserLogin().trim();
			String password = (String) authentication.getCredentials();
			//log.trace("INFO ; ; authenticate({}) ;", userLogin);
			if (userLogin == null || password == null || userLogin.isEmpty() || password.isEmpty()) {
				//log.trace("INFO ; ; authenticate() ; identifiant null ou vide");
				throw new BadCredentialsException("identifiant null ou vide");
			}

			if (!Jsoup.isValid(userLogin, Whitelist.none())) {
				//log.error("ERROR ; ; authenticate() ; identifiant invalide : {}", userLogin);
				throw new BadCredentialsException("identifiant invalide");
			}
			User user = null;
			if (((User) authentication.getPrincipal()).isAnonymousUser()) {
				if (default_login.equals(userLogin) && default_password.equals(password)) {
					user = new User();
					user.setUserLogin("AnonymousUser");
					user.setId(null);
					user.setUuid(uuid);
					user.setAnonymousUser(true);
					user.setPassword(new BCryptPasswordEncoder().encode(password));
					user.grantRole(UserRole.DEFAULT);
				} else
					throw new BadCredentialsException("identifiant invalide");
			} else if (((User) authentication.getPrincipal()).isVeloUser()) {
				user = new User();
				user.setUserLogin(userLogin);
				user.setPassword(new BCryptPasswordEncoder().encode(password));
				user.setUuid(uuid);
				user.grantRole(UserRole.USER_VELO);
				user.setVeloUser(true);
			} else {

				user = loginService.authenticate(uuid, userLogin, password);

			}

			return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
		}
		catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
