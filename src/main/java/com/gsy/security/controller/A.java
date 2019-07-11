package com.gsy.security.controller;

import com.gsy.security.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class A {

	@RequestMapping("/aaa")
	@ResponseBody
	public String aaa(String name){
		System.out.println(name);
		return "aaa";
	}

	@PostMapping("/bbb")
	@ResponseBody
	public String bbb(@RequestBody Student stu){
		System.out.println(stu.getName());
		return "bbb";
	}

	@PostMapping("/ccc")
	@ResponseBody
	public String ccc(@RequestBody String stu){
		try {
			System.out.println(URLDecoder.decode(stu,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("first");
		System.out.println("second");
		System.out.println("third");
		return "bbbb";
	}
}
