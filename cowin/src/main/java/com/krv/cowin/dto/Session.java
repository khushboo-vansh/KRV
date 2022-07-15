package com.krv.cowin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Session {

	@JsonProperty("center_id")
	private Integer centerId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("address")
	private String address;

	@JsonProperty("state_name")
	private String stateName;

	@JsonProperty("district_name")
	private String districtName;

	@JsonProperty("block_name")
	private String blockName;

	@JsonProperty("pincode")
	private Integer pincode;

	@JsonProperty("from")
	private String from;

	@JsonProperty("to")
	private String to;

	@JsonProperty("lat")
	private Integer lat;

	@JsonProperty("long")
	private Integer llong;

	@JsonProperty("fee_type")
	private String feeType;

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("date")
	private String date;

	@JsonProperty("available_capacity")
	private Integer availableCapacity;

	@JsonProperty("available_capacity_dose1")
	private Integer availableCapacityDose1;

	@JsonProperty("available_capacity_dose2")
	private Integer availableCapacityDose2;

	@JsonProperty("fee")
	private String fee;

	@JsonProperty("min_age_limit")
	private Integer minAgeLimit;

	@JsonProperty("allow_all_age")
	private Boolean allowAllAge;

	@JsonProperty("vaccine")
	private String vaccine;

	@JsonProperty("slots")
	private String[] slots;

}