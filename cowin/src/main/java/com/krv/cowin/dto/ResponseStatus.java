package com.krv.cowin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Khushboo_Vansh
 *
 */
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public final class ResponseStatus {

	private Integer statusCode;
	private String statusDescription;

	public static final ResponseStatus SUCCESS = new ResponseStatus(200000, "Success");
	public static final ResponseStatus MISSING_MANDATORY_PARAMETER = new ResponseStatus(400001,
			"Missing Manadatory Parameter");
	public static final ResponseStatus INVALID_PARAMETER_VALUE = new ResponseStatus(400002,
			"Invalid value for the parameter");
	public static final ResponseStatus NO_RESPONSE_CONTENT = new ResponseStatus(400003, "No Records Found");
	public static final ResponseStatus INVALID_TOKEN = new ResponseStatus(400005, "Invalid Token");
	public static final ResponseStatus INVALID_USER = new ResponseStatus(400006, "Invalid User");
	public static final ResponseStatus UNSUPPORTED_FORMAT = new ResponseStatus(400007, "Unsupported File Format ");
	public static final ResponseStatus INVALID_EXCEL_TEMPLATE = new ResponseStatus(400008, "Invalid Excel Template");
	public static final ResponseStatus DATA_ALREADY_EXISTS = new ResponseStatus(400009, "Data Already Exists ");
	public static final ResponseStatus INVALID_USER_TOKEN = new ResponseStatus(400010, "Invalid/Expired User Token");

	public static final ResponseStatus FILE_NAME_SIZE_EXCEEDED = new ResponseStatus(400011, "File Size Exceeds 20 MB ");
	public static final ResponseStatus FILE_SIZE_EXCEEDED = new ResponseStatus(400012,
			"File Name Size Exceeds 50 Characters ");
	public static final ResponseStatus MODEL_IMAGE_NOT_FOUND = new ResponseStatus(400013, "Model Image Not found ");

	public static final ResponseStatus FILE_NOT_FOUND = new ResponseStatus(400014, "Files not found");
	public static final ResponseStatus USER_NOT_ALLOWED = new ResponseStatus(400015, "User not allowed to edit/create");
	public static final ResponseStatus INVALID_PAYLOAD = new ResponseStatus(400016, "Provide valid payload");
	public static final ResponseStatus FILE_ALREADY_EXISTS = new ResponseStatus(400017, "File already exists ");
	public static final ResponseStatus USER_ALREADY_EXISTS = new ResponseStatus(400018, "User already exists");

	public static final ResponseStatus USER_NOT_FOUND = new ResponseStatus(400020, "User Not Found ");
	public static final ResponseStatus VALUE_CHANGE_NOT_PERMITTED = new ResponseStatus(400027,
			"Not allowed to change the parameter");
	public static final ResponseStatus UNABLE_TO_DELETE = new ResponseStatus(400028, "Unable to delete");
	public static final ResponseStatus UNABLE_TO_UPDATE = new ResponseStatus(400030, "Unable to update ");
	public static final ResponseStatus UNABLE_TO_FETCH = new ResponseStatus(400031, "Unable to fetch");

	public static final ResponseStatus NO_EXT_RESPONSE = new ResponseStatus(400041,
			"Requested data not found in the system");

	public static final ResponseStatus INTERNAL_SERVER_ERROR = new ResponseStatus(500000, "Internal System Error");
	public static final ResponseStatus UNABLE_TO_PROCESS = new ResponseStatus(500001, "Unable to process");
	public static final ResponseStatus EXTERNAL_SERVER_ERROR = new ResponseStatus(501001, "External System Error");

	public static final ResponseStatus USER_DENIED = new ResponseStatus(400045,
			"User doesn't have permission to access");

}
