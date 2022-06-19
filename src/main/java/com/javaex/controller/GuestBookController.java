package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	@Autowired
	private GuestBookService gService;
	
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		//System.out.println("GuestBookController: addList");
		List<GuestVo> gList = gService.getList();
		model.addAttribute("gList", gList);
		return "guestbook/addList";
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestVo gVo) {
		//System.out.println("GuestBookController: add");
		int count = gService.insert(gVo);
		return "redirect:/guestbook/addList";
	}
	
	@RequestMapping(value = "/deleteForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(Model model, @PathVariable int no) {
		//System.out.println("GuestBookController: deleteForm");
		model.addAttribute("no", no);
		return "guestbook/deleteForm";
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(@RequestParam int no,
						@RequestParam String password) {
		//System.out.println("GuestBookController: delete");
		int count = gService.delete(no, password);

		if(count > 0) { //삭제 성공일 경우 메인으로 돌아가기
			return "redirect:/guestbook/addList";
		}
		else { //틀릴 경우 새로고침하기
			return "redirect:/guestbook/deleteForm/" + no;
		}
	}
}
