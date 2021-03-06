package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;

@Controller
@RequestMapping(value="/api/user")
public class ApiUserController {
	@Autowired
	private UserService uService;
	
	@ResponseBody
	@RequestMapping(value="/checkId", method= {RequestMethod.GET, RequestMethod.POST})
	public boolean checkId(@RequestBody String id) {
		boolean check = uService.checkId(id);
		return check;
	}
}
