package com.gsy.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class KungfuController {

	private static final Logger logger = Logger.getLogger(KungfuController.class.getName());

	private final String PREFIX = "pages/";
	private final String PREFIX2 = "signin/";
	/**
	 * 欢迎页
	 * @return
	 */
	@GetMapping("/")
	public String index() {
		return "welcome";
	}
	
	/**
	 * 登陆页
	 * @return
	 */
	@GetMapping("/userlogin")
	public String loginPage() {
		return PREFIX+"login";
	}
	
	
	/**
	 * level1页面映射
	 * @param path
	 * @return
	 */
	@GetMapping("/level1/{path}")
	public String level1(@PathVariable("path")String path) {
		return PREFIX+"level1/"+path;
	}
	
	/**
	 * level2页面映射
	 * @param path
	 * @return
	 */
	@GetMapping("/level2/{path}")
	public String level2(@PathVariable("path")String path) {
		return PREFIX+"level2/"+path;
	}
	
	/**
	 * level3页面映射
	 * @param path
	 * @return
	 */
	@GetMapping("/level3/{path}")
	public String level3(@PathVariable("path")String path) {
		return PREFIX+"level3/"+path;
	}

	@GetMapping("/selfLogout")
	public String selfLogout(){
		logger.info("111");
		return "logout";
	}

	@GetMapping("/userRegister")
	public String userRegister() {
		return PREFIX2+"index";
	}

}
