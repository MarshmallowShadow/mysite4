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
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RBoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.RBoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/rboard")
public class RBoardController {
	@Autowired
	private RBoardService rService;
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, @RequestParam(value="keyword", required=false, defaultValue="") String keyword) {
		//System.out.println("BoardController: addList");
		List<Map<String, Object>> rList = rService.getList(keyword);
		model.addAttribute("rList", rList);
		return "rboard/list";
	}
	
	@RequestMapping(value="/delete/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable int no) {
		//System.out.println("BoardController: delete");
		int count = rService.delete(no);
		return "redirect:/rboard/list";
	}
	
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(@RequestParam("groupNo") int groupNo, @RequestParam("orderNo") int orderNo, @RequestParam("depth") int depth, Model model) {
		//System.out.println("BoardController: writeForm");
		RBoardVo rVo = new RBoardVo();
		rVo.setGroupNo(groupNo);
		rVo.setOrderNo(orderNo);
		rVo.setDepth(depth);
		
		model.addAttribute("rVo", rVo);
		return "rboard/writeForm";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute RBoardVo rVo, HttpSession session) {
		//System.out.println("BoardController: write");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		rVo.setUserNo(authUser.getNo());
		
		//convert line break from java to html
		String edit = rVo.getContent().replace("\n", "<br>");
		rVo.setContent(edit);
		
		int count = rService.insert(rVo);
		return "redirect:/rboard/list";
	}
	
	@RequestMapping(value="/read/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @PathVariable int no) {
		//System.out.println("BoardController: read");
		
		//정보 가져오기
		Map<String, Object> rMap = rService.getRBoard(true, no);
		model.addAttribute("rMap", rMap);
		return "rboard/read";
	}
	
	@RequestMapping(value="/modifyForm/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @PathVariable int no) {
		//System.out.println("BoardController: modifyForm");
		
		Map<String, Object> rMap = rService.getRBoard(false, no);
		
		//convert line break from HTML to java
		String edit = ((String)rMap.get("CONTENT")).replace("<br>", "\n");
		rMap.put("CONTENT", edit);
		
		model.addAttribute("bMap", rMap);
		return "rboard/modifyForm";
	}
	
	@RequestMapping(value="/modify/{no}", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute RBoardVo rVo, @PathVariable int no) {
		//System.out.println("BoardController: modify");
		
		//convert line break from java to html
		String edit = rVo.getContent().replace("\n", "<br>");
		rVo.setContent(edit);
		rVo.setNo(no);
		
		int count = rService.modify(rVo);
		return "redirect:/rboard/list";
	}
}
