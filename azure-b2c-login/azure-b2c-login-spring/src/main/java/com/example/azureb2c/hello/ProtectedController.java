package com.example.azureb2c.hello;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProtectedController {

	@GetMapping("/group1")
	@ResponseBody
	@PreAuthorize("hasRole('piotreks-grup')")
	public String group1() {
		return "{ \"protected\": 1}";
	}

	@GetMapping("/group2")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_group2')")
	public String group2() {
		return "{ \"protected\": 2}";
	}
}
