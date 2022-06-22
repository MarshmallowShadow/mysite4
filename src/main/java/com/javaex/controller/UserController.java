package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService uService;
	
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		//System.out.println("UserController: joinForm");
		return "user/joinForm";
	}
	
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo uVo) {
		//System.out.println("UserController: join");
		
		int count = uService.join(uVo);
		
		if(count > 0) {
			return "user/joinOk";
		} else {
			return "user/joinForm?signup=fail";
		}
	}
	
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		//System.out.println("UserController: loginForm");
		return "user/loginForm";
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo uVo, HttpSession session) {
		//System.out.println("UserController: login");
		
		UserVo authUser = uService.login(uVo);
		session.setAttribute("authUser", authUser);
		
		
		if(authUser == null) {
			return "redirect:/user/loginForm?login=fail";
		} else {
			return "redirect:/main";
		}
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		//System.out.println("UserController: logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, HttpSession session) {
		//System.out.println("UserController: modifyForm");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		UserVo uVo = uService.getUser(no);
		model.addAttribute("uVo", uVo);
		
		return "user/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo uVo, HttpSession session) {
		//System.out.println("UserController: modify");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		uVo.setNo(no);
		
		int count = uService.update(uVo);
		
		authUser.setName(uVo.getName());
		return "redirect:/main";
	}
}
