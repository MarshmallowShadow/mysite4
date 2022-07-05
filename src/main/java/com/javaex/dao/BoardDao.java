package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	//글 작성 후에 추가
	public int insert(BoardVo bVo) {
		int count = sqlSession.insert("board.insert", bVo);
		return count;
	}
	
	//글 삭제
	public int delete(int no) {
		int count = sqlSession.delete("board.delete", no);
		return count;
	}
	
	//게시글 누를때마다 조회수 1개 오르기
	public int plusView(int no) {
		int count = sqlSession.update("board.plusView", no);
		return count;
	}
	
	//게시글 제목, 내용 수정
	public int modify(BoardVo bVo) {
		int count = sqlSession.update("board.modify", bVo);
		return count;
	}
	
	//게시판에 나열할 게시글 목록 가져오기
	public List<Map<String, Object>> getList(String keyword){
		List<Map<String, Object>> bList;
		bList = sqlSession.selectList("board.getList", keyword);
		return bList;
	}
	
	//게시판에 나열할 게시글 목록 가져오기
	public List<Map<String, Object>> getPageList(Map<String, Object> pMap){
		List<Map<String, Object>> bList;
		bList = sqlSession.selectList("board.getPageList", pMap);
		return bList;
	}
	
	
	//read에 필요한 해당 개시글에 정보 가져오기
	public Map<String, Object> getBoard(int no) {
		Map<String, Object> bMap = sqlSession.selectOne("board.getBoard", no);
		return bMap;
	}
	
	//총 글 수 가져오기
	public int getCount() {
		int count = sqlSession.selectOne("board.getCount");
		return count;
	}
}
