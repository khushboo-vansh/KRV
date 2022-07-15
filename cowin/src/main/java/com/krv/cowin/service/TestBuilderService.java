package com.krv.cowin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krv.cowin.dto.UserDto;
import com.krv.cowin.dto.UserDto.Builder;

@Service
public class TestBuilderService {

	public List<UserDto> getUser() {
		List<UserDto> result = new ArrayList<>();
		result.add(UserDto.newBuilder().withAge(35).withDepartment("Test Dept").withName("Khushboo Vansh").build());
		result.add(getUser2());
		result.add(UserDto.newBuilder(getUser2()).build());
		return result;
	}

	private UserDto getUser2() {
		Builder userBuilder = UserDto.newBuilder();
		userBuilder = userBuilder.withAge(35);
		userBuilder = userBuilder.withDepartment("Computer Science");
		return userBuilder.withName("Khushboo Vansh").build();
	}

}
