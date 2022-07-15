package com.krv.my.app.authenticate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krv.my.app.authenticate.dto.ResponseStatus;
import com.krv.my.app.authenticate.dto.UserDto;
import com.krv.my.app.authenticate.entity.UserEntity;
import com.krv.my.app.authenticate.exception.RequestValidationException;
import com.krv.my.app.authenticate.repository.UserRepository;
import com.krv.my.app.authenticate.util.JwtTokenUtil;

/**
 * @author Khushboo_Vansh
 *
 */
@Service
public class TokenManagementService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * @param userName
	 * @return
	 */
	public String getToken(String userName) {
		String token = null;
		UserEntity usersEntity = userRepository.findByUserName(userName);
		if (null == usersEntity) {
			throw new RequestValidationException(ResponseStatus.INVALID_USER);
		} else {
			token = JwtTokenUtil
					.generateToken(UserDto.builder().userName(userName).email(usersEntity.getEmail()).build());
		}
		return token;
	}

	/**
	 * @param userName
	 * @param token
	 */
	public void validateToken(String userName, String token) {
		if (!JwtTokenUtil.isValidToken(token, userName)) {
			throw new RequestValidationException(ResponseStatus.INVALID_USER_TOKEN);
		}
	}

}
