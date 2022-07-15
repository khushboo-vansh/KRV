package com.krv.my.app.authenticate.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.krv.my.app.authenticate.dto.UserDto;
import com.krv.my.app.authenticate.util.JwtTokenUtil;

/**
 * @author Khushboo_Vansh
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;
	private static final String AUTHORIZE = "authorize";
	private static final String EXCEPTION_ALLOW = "exception-allow";
	private static final String MESSAGE = "Unauthorized : Invalid or Expired Token";
	private static final String USERS_TOKEN = "Users-Token";

	/**
	 * JWT Entry Point Configurations
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		if ((null != request.getAttribute(EXCEPTION_ALLOW)) && request.getAttribute(EXCEPTION_ALLOW).equals(true)) {
			request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
		} else if ((null != request.getAttribute(AUTHORIZE)) && request.getAttribute(AUTHORIZE).equals(false)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MESSAGE);
		} else {
			String token = request.getHeader(USERS_TOKEN);
			UserDto user = JwtTokenUtil.parseToken(token);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					user.getUserName(), user.getEmail());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
		}
	}
}
