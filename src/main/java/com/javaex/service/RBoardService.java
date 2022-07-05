package com.javaex.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {
	@Autowired
	private RBoardDao rDao;
	
	public int insert(RBoardVo rVo) {
		int count;
		
		if(rVo.getGroupNo() == 0) {
			count = rDao.insert(rVo);
		} else {
			int count2 = rDao.plusOrderNo(rVo.getOrderNo());
			count = rDao.comment(rVo);
		}
		
		return count;
	}
	
	public int delete(int no) {
		int count = rDao.delete(no);
		return count;
	}
	
	public int modify(RBoardVo rVo) {
		int count = rDao.modify(rVo);
		return count;
	}
	
	public List<Map<String, Object>> getList(String keyword, int page){
		Map<String, Object> rMap = new HashMap<>();
		int startBoard = (page-1) * 10 + 1;
		int endBoard = page * 10;
		rMap.put("keyword", keyword);
		rMap.put("startBoard", startBoard);
		rMap.put("endBoard", endBoard);
		
		List<Map<String, Object>> rList = rDao.getList(rMap);
		
		for(int i=0; i<rList.size(); i++) {
			for(int j=0; j < ((BigDecimal)rList.get(i).get("DEPTH")).intValue(); j++) {
				rList.get(i).put("TITLE", (Object)("&emsp;" + rList.get(i).get("TITLE")));
			}
		}
		
		return rList;
	}
	
	public Map<String, Object> getRBoard(boolean hit, int no) {
		if(hit) {
			int count = rDao.plusView(no);
		}
		
		Map<String, Object> rMap = rDao.getRBoard(no);
		return rMap;
	}
	
	public int getPages() {
		int count = rDao.getCount();
		if (count % 10 != 0) {
			count = count / 10 + 1;
		} else {
			count = count / 10;
		}
		return count;
	}
}
