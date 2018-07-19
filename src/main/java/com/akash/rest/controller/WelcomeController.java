package com.akash.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akash.beans.Welcome;

@RestController
public class WelcomeController {

	private static final String welcomeMessage = "Welcome Dear %s!";

	@GetMapping("/welcome/user")
	public Welcome welcomeUser(@RequestParam(name = "name", required = false, defaultValue = "Boot Fan") String name) {
		return new Welcome(String.format(welcomeMessage, name));
	}
}
