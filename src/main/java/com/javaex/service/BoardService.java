package com.javaex.service;

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
	
	public Map<String, Object> read(int no) {
		//조회수 1 증가 (Business Logic)
		int count = bDao.plusView(no);
		Map<String, Object> bMap = bDao.getBoard(no);
		return bMap;
	}
	
	public Map<String, Object> getBoard(int no) {
		Map<String, Object> bMap = bDao.getBoard(no);
		return bMap;
	}
}
