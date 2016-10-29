package org.gestion.bp.ath0.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gestion.bp.ath0.model.UserAuthentication;
import org.gestion.bp.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service("tokenAuthenticationService")
public class TokenAuthenticationService {

	private static final String	AUTH_HEADER_NAME	= "X-AUTH-TOKEN";
	private static final long	TEN_DAYS			= 1000 * 60 * 60 * 24 * 10;
	private static final long	ONE_DAY				= 1000 * 60 * 60 * 24 * 1;
	private static final long	ONE_YEAR			= 1000 * 60 * 60 * 24 * 365;

	private final TokenHandler	tokenHandler;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret) {
		tokenHandler = new TokenHandler(secret.getBytes());
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
		final User user = authentication.getDetails();

		user.setExpires(System.currentTimeMillis() + ONE_DAY);

		if (user.isVeloUser()) {
			user.setExpires(System.currentTimeMillis() + ONE_YEAR);
		}
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
