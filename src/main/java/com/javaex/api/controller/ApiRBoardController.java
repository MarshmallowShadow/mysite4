package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.RBoardService;

@RequestMapping(value="/api/rboard")
@Controller
public class ApiRBoardController {
	@Autowired
	private RBoardService rService;
	
	@ResponseBody
	@RequestMapping(value="/getPages", method= {RequestMethod.GET, RequestMethod.POST})
	public int getPages() {
		int count = rService.getPages();
		return count;
	}
}
