package com.krv.my.app.authenticate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Khushboo_Vansh
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private int id;
	private String pname;
	private String batchNo;
	private double price;
	private int noOfproduct;

}
