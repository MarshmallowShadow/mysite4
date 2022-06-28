package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestVo;

@Controller
public class ApiGuestBookController {
	@Autowired
	private GuestBookService gService;
	
	@RequestMapping(value="/api/guestbook/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		//System.out.println("api/guestbook: addList");
		return "apiGuestbook/addList";
	}
	
	@ResponseBody
	@RequestMapping(value="/api/guestbook/list", method={RequestMethod.GET, RequestMethod.POST})
	public List<GuestVo> list() {
		//System.out.println("api/guestbook: list");
		List<GuestVo> gList = gService.getList();
		//System.out.println(gList);
		return gList;
	}
}
