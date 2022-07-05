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

	public List<Map<String, Object>> getList(String keyword) {
		List<Map<String, Object>> bList = bDao.getList(keyword);
		return bList;
	}

	public Map<String, Object> getPageList(String keyword, int page){
		//
		Map<String, Object> bMap = new HashMap<>();
		int startBoard = (page-1) * 10 + 1;
		int endBoard = page * 10;
		bMap.put("keyword", keyword);
		bMap.put("startBoard", startBoard);
		bMap.put("endBoard", endBoard);
		
		List<Map<String, Object>> bList = bDao.getPageList(bMap);
		
		int pageBtnCount = 5;
		int listCnt = 10;
		int totalCnt = bDao.getCount();
		int endPageBtnNo = (int)Math.ceil(page/(double)pageBtnCount)*pageBtnCount;
		int startPageBtnNo = (endPageBtnNo - pageBtnCount) + 1;
		
		boolean next = false;
    	if(listCnt*endPageBtnNo < totalCnt) {
    		next = true;
    	}else {
    		endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);  
    	}
    	
    	boolean prev = false;
        if(startPageBtnNo != 1) {
           prev = true;
        }
        
        Map<String, Object> pMap = new HashMap<String, Object>();
        pMap.put("bList", bList);
        pMap.put("prev", prev);
        pMap.put("startPageBtnNo", startPageBtnNo);
        pMap.put("endPageBtnNo", endPageBtnNo);
        pMap.put("next", next);

		
		return pMap;
	}

	public Map<String, Object> getBoard(boolean hit, int no) {
		if (hit) {
			int count = bDao.plusView(no);
		}
		Map<String, Object> bMap = bDao.getBoard(no);
		return bMap;
	}

	// test
	public int massInsert() {
		BoardVo bVo = new BoardVo();
		bVo.setUserNo(0);
		bVo.setTitle("누나");
		bVo.setContent("마음이 아프다");

		int count = 0;

		for (int i = 0; i < 100; i++) {
			count += bDao.insert(bVo);
		}
		return count;
	}
}
