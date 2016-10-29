package org.gestion.bp.ath0.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.gestion.bp.ath0.model.UserAuthentication;
import org.gestion.bp.ath0.service.TokenAuthenticationService;
import org.gestion.bp.dto.user.User;
import org.gestion.bp.service.IFoLoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService	tokenAuthenticationService;
	private final IFoLoginService				loginService;

	protected StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService, IFoLoginService loginService, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		this.loginService = loginService;
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

		final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(user, user.getPassword());
		return getAuthenticationManager().authenticate(loginToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException,
			ServletException {

		// Lookup the complete User object from the database and create an Authentication for it
		final User authenticatedUser = (User) authentication.getPrincipal();
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}
}