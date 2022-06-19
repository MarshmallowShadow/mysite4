package com.javaex.dao;

import java.util.List;
import java.util.Map;

public class TestApp {
	public static void main(String[] args) {
		BoardDao boardDao = new BoardDao();
		
		List<Map<String, Object>> boardVo = boardDao.getList("");
		System.out.println(boardVo);
	}
}
