package com.krv.my.app.authenticate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krv.my.app.authenticate.dto.ProductDto;

/**
 * @author Khushboo_Vansh
 *
 */
@Service
public class ProductService {

	/**
	 * @return
	 */
	public List<ProductDto> findAll() {
		ArrayList<ProductDto> products = new ArrayList<ProductDto>();
		products.add(new ProductDto(100, "Mobile", "CLK98123", 9000.00, 6));
		products.add(new ProductDto(101, "Smart TV", "LGST09167", 60000.00, 3));
		products.add(new ProductDto(102, "Washing Machine", "38753BK9", 9000.00, 7));
		products.add(new ProductDto(103, "Laptop", "LHP29OCP", 24000.00, 1));
		products.add(new ProductDto(104, "Air Conditioner", "ACLG66721", 30000.00, 5));
		products.add(new ProductDto(105, "Refrigerator ", "12WP9087", 10000.00, 4));
		return products;
	}

}
