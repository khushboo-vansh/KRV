package com.krv.proxy.zuulserver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

	@GetMapping("/foos/{id}")
	public String findById(@PathVariable long id, HttpServletRequest req, HttpServletResponse res) {
		if (req.getHeader("Test") != null) {
			res.addHeader("Test", req.getHeader("Test"));
		}
		return new String("test");
	}
}