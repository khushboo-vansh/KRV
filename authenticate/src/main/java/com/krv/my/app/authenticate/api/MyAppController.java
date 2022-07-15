package com.krv.my.app.authenticate.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krv.my.app.authenticate.dto.ProductDto;
import com.krv.my.app.authenticate.service.ProductService;

/**
 * @author Khushboo_Vansh
 *
 */
@RestController
public class MyAppController {

	@Autowired
	private ProductService productService;

	/**
	 * @return
	 */
	@GetMapping(value = "/product")
	public List<ProductDto> getProduct() {
		List<ProductDto> products = productService.findAll();
		return products;
	}

}
