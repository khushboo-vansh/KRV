package com.krv.cowin.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.krv.cowin.constant.APIConstants;
import com.krv.cowin.constant.Constants;
import com.krv.cowin.dto.AuthOtpConfirmationDto;
import com.krv.cowin.dto.AuthOtpTokenDto;
import com.krv.cowin.dto.AuthRequestDto;
import com.krv.cowin.dto.AuthResponseDto;
import com.krv.cowin.dto.AvailabilityResponseDto;
import com.krv.cowin.dto.ResponseStatus;
import com.krv.cowin.dto.Session;
import com.krv.cowin.exception.ExternalSystemException;
import com.krv.cowin.exception.ProcessingException;
import com.krv.cowin.exception.RequestValidationException;
import com.krv.cowin.util.MobileNumberValidationUtility;

/**
 * @author Khushboo_Vansh
 *
 */
@Service
public class CowinApiService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CowinApiService.class);

	@Autowired
	private RestTemplate restTemplate;

	private final String URI_BASE = "https://cdn-api.co-vin.in/api/v2/";
	private final String URI_GENERATE_OTP = "auth/public/generateOTP";
	private final String URI_CONFIRM_OTP = "auth/public/confirmOTP";
	private final String URI_GET_SLOTS = "appointment/sessions/public/findByPin";

	public List<Session> getSlots(String pincode) {
		validatePincode(pincode);
		List<Session> sessions = null;
		ResponseEntity<AvailabilityResponseDto> result = null;
		try {
			HttpEntity<?> input = new HttpEntity<>(buildRequest());
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String today = df.format(new Date());
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URI_BASE + URI_GET_SLOTS)
					.queryParam("pincode", pincode).queryParam("date", today);
			result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, input, AvailabilityResponseDto.class);

		} catch (Exception e) {
			throw new ExternalSystemException("COWIN", e);
		}
		if (null != result.getBody() && null != result.getBody().getSessions()) {
			sessions = result.getBody().getSessions();
		} else {
			throw new RequestValidationException(ResponseStatus.NO_RESPONSE_CONTENT);
		}
		return sessions;
	}

	private void validatePincode(String pincode) {
		if (null == pincode || pincode.trim().isEmpty()) {
			throw new RequestValidationException(ResponseStatus.INVALID_PARAMETER_VALUE);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public AuthResponseDto getOtp(AuthRequestDto request) {
		validateAuthRequest(request);
		String token = null;
		ResponseEntity<AuthResponseDto> result = null;
		try {
			HttpEntity<?> input = new HttpEntity<>(request, buildRequest());
			result = restTemplate.exchange(URI_BASE + URI_GENERATE_OTP, HttpMethod.POST, input, AuthResponseDto.class);

		} catch (Exception e) {
			throw new ExternalSystemException("COWIN", e);
		}
		if (null != result || null != result.getBody().getTxnId()) {
			token = result.getBody().getTxnId();
		} else {
			throw new RequestValidationException(ResponseStatus.NO_RESPONSE_CONTENT);
		}
		return AuthResponseDto.builder().txnId(token).build();
	}

	public static byte[] getSHA(String input) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new ProcessingException(e);
		}
		return md.digest(input.getBytes(StandardCharsets.UTF_8));

	}

	public static String toHexString(byte[] hash) {
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}

	/**
	 * @param request
	 * @return
	 */
	public AuthOtpTokenDto confirmOtp(AuthOtpConfirmationDto request) {
		validateConfirmOtp(request);
		String token = null;
		String hash = toHexString(getSHA(request.getOtp()));
		request.setOtp(hash);
		ResponseEntity<AuthOtpTokenDto> result = null;
		try {
			HttpEntity<?> input = new HttpEntity<>(request, buildRequest());
			result = restTemplate.exchange(URI_BASE + URI_CONFIRM_OTP, HttpMethod.POST, input, AuthOtpTokenDto.class);

		} catch (HttpClientErrorException ex) {
			throw new RequestValidationException(ex.getLocalizedMessage());
		} catch (Exception e) {
			throw new ExternalSystemException("COWIN", e);
		}
		if (null != result.getBody() && null != result.getBody().getToken()) {
			token = result.getBody().getToken();
		} else {
			throw new RequestValidationException(ResponseStatus.NO_RESPONSE_CONTENT);
		}
		return AuthOtpTokenDto.builder().token(token).build();
	}

	private void validateConfirmOtp(AuthOtpConfirmationDto request) {
		if (null == request.getTxnId() || request.getTxnId().trim().isEmpty()) {
			throw new RequestValidationException(ResponseStatus.INVALID_PARAMETER_VALUE);
		}
		if (null == request.getOtp() || request.getOtp().trim().isEmpty()) {
			throw new RequestValidationException(ResponseStatus.INVALID_PARAMETER_VALUE);
		}
	}

	private void validateAuthRequest(AuthRequestDto request) {
		if (null == request.getMobile() || request.getMobile().trim().isEmpty()
				|| !MobileNumberValidationUtility.isValidMobileNo(request.getMobile())) {
			throw new RequestValidationException(ResponseStatus.INVALID_PARAMETER_VALUE);
		}
	}

	private HttpHeaders buildRequest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(Constants.USER_AGENT, Constants.USER_AGENT_VALUE);
		return headers;
	}

	public JSONObject findSlotsByDistrict(String districtId, String date) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(APIConstants.DISTRICT_API)
					.queryParam(Constants.DISTRICT_ID, districtId).queryParam(Constants.DATE, date);
			HttpEntity<?> request = new HttpEntity<>(buildRequest());
			ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
					request, String.class);
			logger.info("receiving status: " + response.getStatusCode());
			return new JSONObject(response.getBody());
		} catch (RestClientResponseException ex) {
			return new JSONObject("{}", ex.getStatusText());
		}
	}

}
