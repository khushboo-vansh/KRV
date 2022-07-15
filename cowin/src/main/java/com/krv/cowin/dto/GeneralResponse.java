package com.krv.cowin.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * General Response format
 *
 * @param <T>
 */
@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GeneralResponse<T> {

	private Integer statusCode;
	private String statusDescription;
	private ZonedDateTime dateTime;
	private Long totalCount;
	private Integer currentCount;
	private T data;

	/**
	 * General Response without data
	 *
	 * @param code
	 * @param desciption
	 * @param date
	 */
	public GeneralResponse(Integer code, String desciption, ZonedDateTime date) {
		this.statusCode = code;
		this.statusDescription = desciption;
		this.dateTime = date;
	}

	/**
	 * General Response with data
	 *
	 * @param data
	 */
	public GeneralResponse(T data) {
		this.statusCode = ResponseStatus.SUCCESS.getStatusCode();
		this.statusDescription = ResponseStatus.SUCCESS.getStatusDescription();
		this.dateTime = ZonedDateTime.now();
		this.data = data;
	}

	/**
	 * @param totalCount
	 * @param currentCount
	 * @param data
	 */
	public GeneralResponse(long totalCount, int currentCount, T data) {
		this.statusCode = ResponseStatus.SUCCESS.getStatusCode();
		this.statusDescription = ResponseStatus.SUCCESS.getStatusDescription();
		this.currentCount = currentCount;
		this.totalCount = totalCount;
		this.dateTime = ZonedDateTime.now();
		this.data = data;
	}
}
