package com.krv.hystrix;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.resilience4j.retry.annotation.Retry;

public class RetryApp {

	@Autowired
	RemoteSearchService remoteSearchService;

	@Retry(name = "flightSearch")
	public List<String> searchFlights(String request) {
		return remoteSearchService.searchFlights(request);
	}

	@Retry(name = "throwingException")
	public List<String> searchFlightsThrowingException(String request) throws Exception {
		return remoteSearchService.searchFlightsThrowingException(request);
	}
}
