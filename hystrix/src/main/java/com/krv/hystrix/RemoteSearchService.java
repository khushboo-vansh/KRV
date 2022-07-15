package com.krv.hystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RemoteSearchService {

	public List<String> searchFlights(String request) {
		List<String> list = new ArrayList<>();
		list.add("main method");
		return list;
	}

	public List<String> searchFlightsThrowingException(String request) {
		List<String> list = new ArrayList<>();
		list.add("fallback method");
		return list;
	}

}
