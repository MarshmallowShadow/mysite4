package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao bDao;
	
	public int insert(BoardVo bVo) {
		int count = bDao.insert(bVo);
		return count;
	}
	
	
	
	public int delete(int no) {
		int count = bDao.delete(no);
		return count;
	}
	
	public int modify(BoardVo bVo) {
		int count = bDao.modify(bVo);
		return count;
	}
	
	public List<Map<String, Object>> getList(String keyword){
		List<Map<String, Object>> bList = bDao.getList(keyword);
		return bList;
	}
	
	public List<Map<String, Object>> getPageList(String keyword, int page){
		Map<String, Object> pMap = new HashMap<>();
		int startBoard = (page-1) * 10 + 1;
		int endBoard = page * 10;
		pMap.put("keyword", keyword);
		pMap.put("startBoard", startBoard);
		pMap.put("endBoard", endBoard);
		
		List<Map<String, Object>> bList = bDao.getPageList(pMap);
		
		int count = bDao.getCount();
		count /= 10;
		if(count%10 != 0) {
			count++;
		}
		
		return bList;
	}
	
	public Map<String, Object> getBoard(boolean hit, int no) {
		if(hit) {
			int count = bDao.plusView(no);
		}
		
		Map<String, Object> bMap = bDao.getBoard(no);
		return bMap;
	}
	
	public int getPages() {
		int count = bDao.getCount();
		if(count%10 != 0) {
			count = count/10 + 1;
		} else {
			count = count/10;
		}
		return count;
	}
	
	//test
	public int massInsert() {
		BoardVo bVo = new BoardVo();
		bVo.setUserNo(0);
		bVo.setTitle("누나");
		bVo.setContent("마음이 아프다");
		
		int count = 0;
		
		for(int i=0; i<100; i++) {
			count += bDao.insert(bVo);
		}
		return count;
	}
}
