package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {
	@RequestMapping("")
public String test(String item) {
		System.out.println("item:"+item);
		return "test";
	}
}
