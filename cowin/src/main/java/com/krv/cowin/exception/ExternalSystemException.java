package com.krv.cowin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krv.cowin.dto.ResponseStatus;

import lombok.Getter;

/**
 * @author NDH00658
 *
 */
@SuppressWarnings("serial")
@Getter
public class ExternalSystemException extends RuntimeException {

	private static final Logger LOG = LoggerFactory.getLogger(ExternalSystemException.class);

	private final Integer errorCode;
	private final String errorDescription;

	/**
	 * @param sourceSystem
	 * @param cause
	 */
	public ExternalSystemException(String sourceSystem, Throwable cause) {
		LOG.error("sourceSystem {}", sourceSystem, cause);
		this.errorCode = ResponseStatus.EXTERNAL_SERVER_ERROR.getStatusCode();
		this.errorDescription = ResponseStatus.EXTERNAL_SERVER_ERROR.getStatusDescription() + " : " + sourceSystem;
	}
}
