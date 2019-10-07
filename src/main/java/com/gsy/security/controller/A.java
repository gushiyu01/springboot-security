package com.gsy.security.controller;

import com.gsy.security.config.MyApplicationContext;
import com.gsy.security.entity.Department;
import com.gsy.security.entity.Student;
import com.gsy.security.mapper.DepartmentMapper;
import com.gsy.security.service.TestReflectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

@Controller
public class A {

	@Autowired
	DepartmentMapper department;

	@RequestMapping("/aaa")
	@ResponseBody
	public Department aaa(String name){
		System.out.println(name);
		Department dept = department.getDeptById(Integer.parseInt(name));
		return dept;
	}

	@RequestMapping("/bbb")
	@ResponseBody
	public String bbb(Student stu){
		System.out.println(stu.getName());
		return "bbb";
	}


	@RequestMapping("/ddd")
	@ResponseBody
	public String ddd(String name){
		try {
			System.out.println(name);
		} catch (Exception e) {

		}
		return name+":gsy";
	}

	@RequestMapping("/testReflect")
	@ResponseBody
	public String testReflect(String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Object service = MyApplicationContext.getBean("testReflectService");
		Method method = service.getClass().getMethod("first",String.class);
		Object invoke = method.invoke(service, name);
		return invoke.toString();
	}
}
