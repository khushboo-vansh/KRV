package com.krv.cowin.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.krv.cowin.dto.GeneralResponse;
import com.krv.cowin.dto.ResponseStatus;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final String MISSING_PARAM = "Missing Mandatory Parameter: ";
	private static final String MISSING_HEADER_PARAM = "Missing Mandatory Header Parameter: ";
	private static final String INVALID_PARAM = ResponseStatus.INVALID_PARAMETER_VALUE.getStatusDescription() + ": ";

	/**
	 * MethodArgumentNotValidException.class
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<GeneralResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String statusDescription = MISSING_PARAM + ex.getBindingResult().getFieldError().getDefaultMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse(
				ResponseStatus.MISSING_MANDATORY_PARAMETER.getStatusCode(), statusDescription, ZonedDateTime.now()));
	}

	/**
	 * MissingRequestHeaderException.class
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = MissingRequestHeaderException.class)
	public ResponseEntity<GeneralResponse> handleMissingServletHeaderParameterException(
			MissingRequestHeaderException ex) {
		String statusDescription = MISSING_HEADER_PARAM + ex.getHeaderName();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse(
				ResponseStatus.MISSING_MANDATORY_PARAMETER.getStatusCode(), statusDescription, ZonedDateTime.now()));
	}

	/**
	 * MissingServletRequestParameterException.class
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<GeneralResponse> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		String statusDescription = MISSING_PARAM + ex.getParameterName();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse(
				ResponseStatus.MISSING_MANDATORY_PARAMETER.getStatusCode(), statusDescription, ZonedDateTime.now()));
	}

	// TODO: To be removed
	/**
	 * HttpMessageNotReadableException.class
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<GeneralResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		String statusDescription = ResponseStatus.INVALID_PAYLOAD.getStatusDescription();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse(
				ResponseStatus.INVALID_PAYLOAD.getStatusCode(), statusDescription, ZonedDateTime.now()));
	}

	/**
	 * MethodArgumentTypeMismatchException.class
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<GeneralResponse> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		String msg = ex.getParameter().getParameterName();
		String statusDescription = INVALID_PARAM + msg;
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeneralResponse(
				ResponseStatus.INVALID_PARAMETER_VALUE.getStatusCode(), statusDescription, ZonedDateTime.now()));
	}

	@ExceptionHandler(value = RequestValidationException.class)
	public ResponseEntity<GeneralResponse> handleRequestValidationException(RequestValidationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new GeneralResponse(ex.getErrorCode(), ex.getErrorDescription(), ZonedDateTime.now()));
	}

	@ExceptionHandler(value = ProcessingException.class)
	public ResponseEntity<GeneralResponse> handleProcessingException(ProcessingException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new GeneralResponse(ex.getErrorCode(), ex.getErrorDescription(), ZonedDateTime.now()));
	}

	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<GeneralResponse> handleRecordNotFoundException(RecordNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new GeneralResponse(ex.getErrorCode(), ex.getErrorDescription(), ZonedDateTime.now()));
	}

	// TODO: Custom HTTP status code 555 to be defined
	@ExceptionHandler(value = ExternalSystemException.class)
	public ResponseEntity<GeneralResponse> handleExternalSystemException(ExternalSystemException e) {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
				.body(new GeneralResponse(e.getErrorCode(), e.getErrorDescription(), ZonedDateTime.now()));
	}

	@ExceptionHandler(value = FileNotFoundException.class)
	public ResponseEntity<GeneralResponse> handleFileNotFoundException(FileNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new GeneralResponse(ex.getErrorCode(), ex.getErrorDescription(), ZonedDateTime.now()));
	}

	@ExceptionHandler(value = InfrastructureException.class)
	public ResponseEntity<GeneralResponse> handleInfrastructureException(InfrastructureException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new GeneralResponse(ex.getErrorCode(), ex.getErrorDescription(), ZonedDateTime.now()));
	}

}
