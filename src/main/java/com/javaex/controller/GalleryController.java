package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	@Autowired
	private GalleryService gService;
	
	//갤러리 이미지들 가져오기
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController: list");
		
		List<Map<String, Object>> gList = gService.getList();
		model.addAttribute("gList", gList);
		return "gallery/list";
	}
	
	//이미지 추가
	@RequestMapping(value="/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam("content") String content, HttpSession session) {
		System.out.println("GalleryController: add");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		
		GalleryVo gVo = new GalleryVo();
		gVo.setUserNo(userNo);
		gVo.setContent(content);
		System.out.println(gVo);
		
		int count = gService.upload(file, gVo);
		
		return "redirect:/gallery/list";
	}
	
	//
}
