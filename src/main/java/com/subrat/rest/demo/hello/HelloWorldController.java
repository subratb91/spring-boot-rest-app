package com.subrat.rest.demo.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(path="/hello-world",method=RequestMethod.GET)
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/user-details")
	public UserDetails getUserDetailsBean() {
		return new UserDetails("Subrat","Behera","Bhubaneswar");
	}
}
