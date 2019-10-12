package com.subrat.rest.demo.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//@RequestMapping(path="/hello-world",method=RequestMethod.GET)
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/user-details")
	public UserDetails getUserDetailsBean() {
		return new UserDetails("Subrat","Behera","Bhubaneswar");
	}
	
	//Alternate way of implementing i18
	/*@GetMapping("/hello-int")
	public String getMessagesini18Format() {
		String message = messageSource.getMessage("label.hello", null,LocaleContextHolder.getLocale());
		return message;
	}*/
	
	@GetMapping("/hello-int")
	public String getMessagesini18Format(@RequestHeader(name="Accept-Language",required=false) String locale) {
		String message = messageSource.getMessage("label.hello", null,new Locale(locale));
		return message;
	}
}
