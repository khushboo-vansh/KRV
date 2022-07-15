package com.krv.cowin.exception;

import com.krv.cowin.dto.ResponseStatus;

import lombok.Getter;

/**
 * @author NDH00658
 *
 */
@SuppressWarnings("serial")
@Getter
public class FileNotFoundException extends RuntimeException {
	private final Integer errorCode;
	private final String errorDescription;

	/**
	 * FileNotFoundException Constructor
	 */
	public FileNotFoundException() {
		this.errorCode = ResponseStatus.FILE_NOT_FOUND.getStatusCode();
		this.errorDescription = ResponseStatus.FILE_NOT_FOUND.getStatusDescription();
	}

}
