package com.krv.my.app.authenticate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		this.errorCode = 400200;
		this.errorDescription = "Unable To Process the Request";
	}
}
