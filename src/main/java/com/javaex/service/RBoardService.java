package com.javaex.service;

import java.math.BigDecimal;
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
	
	public List<Map<String, Object>> getList(String keyword){
		List<Map<String, Object>> rList = rDao.getList(keyword);
		
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
}
