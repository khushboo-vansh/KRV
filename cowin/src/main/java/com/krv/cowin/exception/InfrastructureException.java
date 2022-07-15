package com.krv.cowin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krv.cowin.dto.ResponseStatus;

import lombok.Getter;

/**
 * InfrastructureException
 *
 */
@SuppressWarnings("serial")
@Getter
public class InfrastructureException extends RuntimeException {

	private static final Logger LOG = LoggerFactory.getLogger(InfrastructureException.class);

	private final Integer errorCode;
	private final String errorDescription;

	/**
	 * @param cause
	 */
	public InfrastructureException(Throwable cause) {
		LOG.error(cause.getMessage(), cause);
		this.errorCode = ResponseStatus.INTERNAL_SERVER_ERROR.getStatusCode();
		this.errorDescription = ResponseStatus.INTERNAL_SERVER_ERROR.getStatusDescription();
	}
}
