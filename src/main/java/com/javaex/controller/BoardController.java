package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

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
	public String writeForm() {
		//System.out.println("BoardController: writeForm");
		return "board/writeForm";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo bVo, HttpSession session) {
		//System.out.println("BoardController: write");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		bVo.setUserNo(authUser.getNo());
		
		//convert line break from java to html
		String edit = bVo.getContent().replace("\n", "<br>");
		bVo.setContent(edit);
		
		int count = bService.insert(bVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @PathVariable int no) {
		//System.out.println("BoardController: read");
		
		//정보 가져오기
		Map<String, Object> bMap = bService.getBoard(true, no);
		model.addAttribute("bMap", bMap);
		return "board/read";
	}
	
	@RequestMapping(value="/modifyForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @PathVariable int no) {
		//System.out.println("BoardController: modifyForm");
		
		Map<String, Object> bMap = bService.getBoard(false, no);
		
		//convert line break from HTML to java
		String edit = ((String)bMap.get("CONTENT")).replace("<br>", "\n");
		bMap.put("CONTENT", edit);
		
		model.addAttribute("bMap", bMap);
		return "board/modifyForm";
	}
	
	@RequestMapping(value="/modify/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo bVo, @PathVariable int no) {
		//System.out.println("BoardController: modify");
		
		//convert line break from java to html
		String edit = bVo.getContent().replace("\n", "<br>");
		bVo.setContent(edit);
		bVo.setNo(no);
		
		int count = bService.modify(bVo);
		return "redirect:/board/list";
	}
	
}
