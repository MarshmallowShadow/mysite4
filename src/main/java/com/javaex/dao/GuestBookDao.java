package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;


	public int insert(GuestVo guestVo) {
		int count = sqlSession.insert("guestbook.insert", guestVo);
		return count;
	}


	public int delete(GuestVo gVo) {
		int count = sqlSession.delete("guestbook.delete", gVo);
		return count;
	}


	public List<GuestVo> getList() {
		List<GuestVo> gList = sqlSession.selectList("guestbook.getList");
		return gList;
	}
	
	public GuestVo getGuest(int no) {
		GuestVo gVo = sqlSession.selectOne("guestbook.getGuest", no);
		return gVo;
	}
}
