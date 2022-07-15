package com.krv.my.app.authenticate.api;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.krv.my.app.authenticate.constant.AppConstant;
import com.krv.my.app.authenticate.dto.GeneralResponse;
import com.krv.my.app.authenticate.dto.ResponseStatus;
import com.krv.my.app.authenticate.service.TokenManagementService;

@RestController
public class AuthenticateController {

	@Autowired
	private TokenManagementService tokenManagementService;

	/**
	 * @param userName
	 * @return
	 */
	@GetMapping("/users/token")
	public ResponseEntity<GeneralResponse<String>> getToken(
			@RequestHeader(name = AppConstant.USER, required = true) String userName) {
		String token = tokenManagementService.getToken(userName);
		GeneralResponse<String> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

	/**
	 * @param token
	 * @param userName
	 * @return
	 */
	@GetMapping("/users/token/validate")
	public ResponseEntity<GeneralResponse> validateToken(
			@RequestHeader(name = AppConstant.USERS_TOKEN, required = true) String token,
			@RequestHeader(name = AppConstant.USER, required = true) String userName) {
		tokenManagementService.validateToken(userName, token);
		return ResponseEntity.ok(new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now()));
	}

}
