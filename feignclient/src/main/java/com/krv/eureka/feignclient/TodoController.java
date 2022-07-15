package com.krv.eureka.feignclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	TodoClient todoClient;

	@GetMapping()
	public List<TodoModel> getTodos() {
		return todoClient.getTodos();
	}

}