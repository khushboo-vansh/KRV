package com.krv.my.app.authenticate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krv.my.app.authenticate.dto.ResponseStatus;

import lombok.Getter;

/**
 * @author Khushboo_Vansh
 *
 */
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
}
