package com.ajitesh.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eureka/client")
public class GreetingController {
	
	@GetMapping("/greeting")
	public String getGreeting() {
		
		return "{\"message\" : \"Hello from Erueka client -> 2.\"}";
	}

}
