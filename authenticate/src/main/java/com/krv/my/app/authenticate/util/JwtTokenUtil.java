package com.krv.my.app.authenticate.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krv.my.app.authenticate.dto.ResponseStatus;
import com.krv.my.app.authenticate.dto.UserDto;
import com.krv.my.app.authenticate.exception.ProcessingException;
import com.krv.my.app.authenticate.exception.RequestValidationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

/**
 * @author Khushboo_Vansh
 *
 */
@UtilityClass
public class JwtTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = (90000);
	private static final byte[] SECRET = { 42, 116, 54, 104, 106, 55, 37, 55, 38, 56, 38, 99, 118, 51, 52, 54, 55, 100,
			120, 57, 109, 36, 100, 103, 103, 35, 107, 106, 64, 29 };
	private static final String EMAIL = "email";
	private static final Logger LOG = LoggerFactory.getLogger(JwtTokenUtil.class);

	/**
	 * @param user
	 * @return
	 */
	public String generateToken(UserDto user) {
		String token = null;
		try {
			Claims claims = Jwts.claims().setSubject(user.getUserName());
			claims.put(EMAIL, user.getEmail());
			String jwt = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, new String(SECRET))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY * 1000))).compact();
			token = EncryptionUtil.encrypt(jwt);
		} catch (Exception e) {
			throw new ProcessingException(e);
		}
		return token;

	}

	/**
	 * @param token
	 * @return
	 */
	public UserDto parseToken(String token) {
		UserDto usersDto = null;
		try {
			String decrptToken = EncryptionUtil.decrypt(token);
			Claims body = Jwts.parser().setSigningKey(new String(SECRET)).parseClaimsJws(decrptToken).getBody();
			usersDto = UserDto.builder().userName(body.getSubject()).email(body.get(EMAIL).toString()).build();
		} catch (Exception e) {
			LOG.error("parseToken {} ", e.getLocalizedMessage());
			throw new RequestValidationException(ResponseStatus.INVALID_TOKEN);
		}
		return usersDto;
	}

	/**
	 * @param token
	 * @param userName
	 * @return
	 */
	public boolean isValidToken(String token, String userName) {
		boolean isValid = false;
		UserDto usersDto = parseToken(token);
		if (usersDto.getUserName().equals(userName)) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * @param token
	 * @return
	 */
	public boolean isTokenExpired(String token) {
		boolean isValid = false;
		try {
			Claims body = Jwts.parser().setSigningKey(new String(SECRET)).parseClaimsJws(EncryptionUtil.decrypt(token))
					.getBody();
			isValid = body.getExpiration().before(new Date());
		} catch (Exception e) {
			LOG.error("isTokenExpired {}", e.getLocalizedMessage());
			throw new RequestValidationException(ResponseStatus.INVALID_USER_TOKEN);
		}
		return isValid;
	}

}
