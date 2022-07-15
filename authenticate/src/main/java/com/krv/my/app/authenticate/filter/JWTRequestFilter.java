package com.krv.my.app.authenticate.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.krv.my.app.authenticate.util.JwtTokenUtil;

/**
 * @author Khushboo_Vansh
 *
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JWTRequestFilter.class);
	private static final String AUTHORIZE = "authorize";
	private static final String EXCEPTION_ALLOW = "exception-allow";
	private static final String USERS_TOKEN = "Users-Token";
	private static final String USER_ID = "user";

	private static final String[] ALLOW = { "/users/authenticate", "/users/token", "/files/", "/users", };

	/**
	 * JWT Filter Configurations
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		request.setAttribute(AUTHORIZE, false);
		String path = request.getRequestURI();
		if (request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
			request.setAttribute(AUTHORIZE, true);
		} else if (path.startsWith(ALLOW[0]) || path.startsWith(ALLOW[1]) || path.startsWith(ALLOW[2])
				|| (path.equals(ALLOW[3]))) {
			request.setAttribute(EXCEPTION_ALLOW, true);
		} else {
			String token = request.getHeader(USERS_TOKEN);
			String userId = request.getHeader(USER_ID);
			try {
				if ((null != token) && (null != userId) && JwtTokenUtil.isValidToken(token, userId)) {
					request.setAttribute(AUTHORIZE, true);
				}
			} catch (Exception e) {
				LOG.debug("Invalid Token {} ", e.getLocalizedMessage());
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * The Authorization Filter overridding the destroy() method.
	 */
	@Override
	public void destroy() {
		LOG.info("AuthorizationFilter destroyed");
	}
}
