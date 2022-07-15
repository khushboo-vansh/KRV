package com.krv.cowin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krv.cowin.dto.ResponseStatus;

import lombok.Getter;

/**
 * @author Khushboo_Vansh
 *
 */
@SuppressWarnings("serial")
@Getter
public class RequestValidationException extends RuntimeException {

	private static final Logger LOG = LoggerFactory.getLogger(RequestValidationException.class);

	private final Integer errorCode;
	private final String errorDescription;

	/**
	 * @param cause
	 */
	public RequestValidationException(ResponseStatus responseStatus) {
		LOG.error("RequestValidationException ");
		this.errorCode = responseStatus.getStatusCode();
		this.errorDescription = responseStatus.getStatusDescription();
	}

	public RequestValidationException(String message) {
		LOG.error("RequestValidationException ");
		this.errorCode = 400005;
		this.errorDescription = message;
	}

}
