package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService bService;
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, String keyword) {
		//System.out.println("BoardController: addList");
		List<Map<String, Object>> bList = bService.getList(keyword);
		model.addAttribute("bList", bList);
		return "board/list";
	}
	
	@RequestMapping(value="/delete/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable int no) {
		//System.out.println("BoardController: delete");
		int count = bService.delete(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(Model model, @PathVariable int no) {
		//System.out.println("BoardController: writeForm");
		return "board/writeForm";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo bVo) {
		//System.out.println("BoardController: write");
		int count = bService.insert(bVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @PathVariable int no) {
		//System.out.println("BoardController: read");
		Map<String, Object> bMap = bService.getBoard(no);
		model.addAttribute("bMap", bMap);
		return "board/read";
	}
	
	@RequestMapping(value="/modifyForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @PathVariable int no) {
		//System.out.println("BoardController: modifyForm");
		Map<String, Object> bMap = bService.getBoard(no);
		model.addAttribute("bMap", bMap);
		return "board/modifyForm";
	}
	
	@RequestMapping(value="/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo bVo) {
		//System.out.println("BoardController: modify");
		int count = bService.modify(bVo);
		return "redirect:/board/list";
	}
	
}
