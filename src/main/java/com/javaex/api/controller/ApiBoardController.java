package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BoardService;

@RequestMapping(value="/api/board")
@Controller
public class ApiBoardController {
	@Autowired
	private BoardService bService;
	
	@ResponseBody
	@RequestMapping(value="/getPages", method= {RequestMethod.GET, RequestMethod.POST})
	public int getPages() {
		int count = bService.getPages();
		return count;
	}
}
