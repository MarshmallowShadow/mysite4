package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@RequestMapping(value="/api/gallery")
@Controller
public class ApiGalleryController {
	@Autowired
	GalleryService gService;
	
	@ResponseBody
	@RequestMapping(value="/getGallery", method= {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo getGallery(@RequestBody int no) {
		GalleryVo gVo = gService.getGallery(no);
		return gVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/getGallery", method= {RequestMethod.GET, RequestMethod.POST})
	public int delete(@RequestBody int no) {
		int count = gService.delete(no);
		return count;
	}
}
