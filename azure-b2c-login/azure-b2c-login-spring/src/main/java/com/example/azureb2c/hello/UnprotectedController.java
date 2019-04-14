package com.example.azureb2c.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class UnprotectedController {

	@GetMapping
	public String index() {
		return "{ \"home\": 1}";
	}

}
