package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService uService;
	
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		//System.out.println("UserController: joinForm");
		return "user/joinForm";
	}
	
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join() {
		//System.out.println("UserController: join");
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		//System.out.println("UserController: loginForm");
		return "user/loginForm";
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		//System.out.println("UserController: login");
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout() {
		//System.out.println("UserController: logout");
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm() {
		//System.out.println("UserController: modifyForm");
		
		return "user/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify() {
		//System.out.println("UserController: modify");
		
		return "redirect:/main";
	}
}
