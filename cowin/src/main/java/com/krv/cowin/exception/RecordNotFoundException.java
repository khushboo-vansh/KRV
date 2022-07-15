package com.krv.cowin.exception;

import com.krv.cowin.dto.ResponseStatus;

import lombok.Getter;

/**
 * RecordNotFoundException
 *
 */
@SuppressWarnings("serial")
@Getter
public class RecordNotFoundException extends RuntimeException {

	private final Integer errorCode;
	private final String errorDescription;

	/**
	 * Whenever a database call responds with empty response for the select queries,
	 * throw this exception
	 */
	public RecordNotFoundException() {
		this.errorCode = ResponseStatus.NO_RESPONSE_CONTENT.getStatusCode();
		this.errorDescription = ResponseStatus.NO_RESPONSE_CONTENT.getStatusDescription();
	}
}
