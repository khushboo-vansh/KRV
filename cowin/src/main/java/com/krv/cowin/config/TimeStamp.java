package com.krv.cowin.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.krv.cowin.constant.Constants;

@Component
public class TimeStamp {

	public String getTime() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT));
	}
}