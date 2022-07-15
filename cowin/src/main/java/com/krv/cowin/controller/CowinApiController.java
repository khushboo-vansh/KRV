package com.krv.cowin.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krv.cowin.dto.AuthOtpConfirmationDto;
import com.krv.cowin.dto.AuthOtpTokenDto;
import com.krv.cowin.dto.AuthRequestDto;
import com.krv.cowin.dto.AuthResponseDto;
import com.krv.cowin.dto.GeneralResponse;
import com.krv.cowin.dto.ResponseStatus;
import com.krv.cowin.dto.Session;
import com.krv.cowin.service.CowinApiService;

/**
 * @author Khushboo_Vansh
 *
 */
@RestController
public class CowinApiController {

	@Autowired
	private CowinApiService cowinApiService;

	/**
	 * @param request
	 * @return
	 */
	@PostMapping("/auth/generateOTP")
	public ResponseEntity<GeneralResponse<AuthResponseDto>> getToken(@RequestBody AuthRequestDto request) {
		AuthResponseDto token = cowinApiService.getOtp(request);
		GeneralResponse<AuthResponseDto> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

	/**
	 * @param request
	 * @return
	 */
	@PostMapping("/auth/confirmOTP")
	public ResponseEntity<GeneralResponse<AuthOtpTokenDto>> confirmOtp(@RequestBody AuthOtpConfirmationDto request) {
		AuthOtpTokenDto token = cowinApiService.confirmOtp(request);
		GeneralResponse<AuthOtpTokenDto> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

	/**
	 * @param pincode
	 * @return
	 */
	@GetMapping("/vaccine/slots")
	public ResponseEntity<GeneralResponse<List<Session>>> getVaccineSlots(@RequestParam String pincode) {
		List<Session> token = cowinApiService.getSlots(pincode);
		GeneralResponse<List<Session>> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

}
