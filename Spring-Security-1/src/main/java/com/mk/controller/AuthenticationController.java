package com.mk.controller;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/authentication")
public class AuthenticationController {

	@GetMapping("/home")
	public String home() {
		return "Home page...";
	}

	@GetMapping("/user")
	public String user() {
		return "User page...";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Admin page...";
	}
}
