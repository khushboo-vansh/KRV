package com.krv.cowin.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krv.cowin.dto.GeneralResponse;
import com.krv.cowin.dto.ResponseStatus;
import com.krv.cowin.dto.UserDto;
import com.krv.cowin.service.TestBuilderService;
import com.krv.cowin.service.TestExecutorService;

@RestController
public class TestController {

	@Autowired
	private TestExecutorService testExecutorService;

	@Autowired
	private TestBuilderService TestBuilderService;

	@GetMapping("/test/api/1")
	public ResponseEntity<GeneralResponse<String>> getExecutorService() {
		String token = testExecutorService.testExecutor();
		GeneralResponse<String> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/test/api/2")
	public ResponseEntity<GeneralResponse<String>> getExecutorService1() {
		String token = testExecutorService.testExecutor2();
		GeneralResponse<String> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/test/api/3")
	public ResponseEntity<GeneralResponse<String>> getExecutorService2() {
		String token = testExecutorService.testExecutor3();
		GeneralResponse<String> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/test/api/4")
	public ResponseEntity<GeneralResponse<String>> getExecutorService3() {
		String token = testExecutorService.testExecutor4();
		GeneralResponse<String> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/test/api/5")
	public ResponseEntity<GeneralResponse<String>> getExecutorService4() {
		String token = testExecutorService.testExecutor();
		GeneralResponse<String> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, token);
		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/test/builder")
	public ResponseEntity<GeneralResponse<List<UserDto>>> getUser() {
		List<UserDto> user = TestBuilderService.getUser();
		GeneralResponse<List<UserDto>> generalResponse = new GeneralResponse<>(ResponseStatus.SUCCESS.getStatusCode(),
				ResponseStatus.SUCCESS.getStatusDescription(), ZonedDateTime.now(), null, null, user);
		return ResponseEntity.ok(generalResponse);
	}
}
