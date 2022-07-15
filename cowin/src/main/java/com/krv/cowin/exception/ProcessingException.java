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
public class ProcessingException extends RuntimeException {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessingException.class);

	private final Integer errorCode;
	private final String errorDescription;

	/**
	 * @param cause
	 */
	public ProcessingException(Throwable cause) {
		LOG.error("ProcessingException ", cause);
		this.errorCode = ResponseStatus.UNABLE_TO_PROCESS.getStatusCode();
		this.errorDescription = ResponseStatus.UNABLE_TO_PROCESS.getStatusDescription();
	}
}
