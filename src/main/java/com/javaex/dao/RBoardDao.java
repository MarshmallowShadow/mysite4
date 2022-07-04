package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	//글 작성 후에 추가
	public int insert(RBoardVo rVo) {
		int count = sqlSession.insert("rboard.insert", rVo);
		return count;
	}
	
	//답글
	public int comment(RBoardVo rVo) {
		int count = sqlSession.insert("rboard.comment", rVo);
		return count;
	}
	
	//글 삭제
	public int delete(int no) {
		int count = sqlSession.delete("rboard.delete", no);
		return count;
	}
	
	//게시글 누를때마다 조회수 1개 오르기
	public int plusView(int no) {
		int count = sqlSession.update("rboard.plusView", no);
		return count;
	}
	
	//댓글 달때마다 아래 댓글들 1증가
	public int plusOrderNo(int no) {
		int count = sqlSession.update("rboard.plusOrderNo", no);
		return count;
	}
	
	//게시글 제목, 내용 수정
	public int modify(RBoardVo rVo) {
		int count = sqlSession.update("rboard.modify", rVo);
		return count;
	}
	
	//게시판에 나열할 게시글 목록 가져오기
	public List<Map<String, Object>> getList(String keyword){
		List<Map<String, Object>> rList = sqlSession.selectList("rboard.getList", keyword);
		return rList;
	}
	
	
	//read에 필요한 해당 개시글에 정보 가져오기
	public Map<String, Object> getRBoard(int no) {
		Map<String, Object> rMap = sqlSession.selectOne("rboard.getRBoard", no);
		return rMap;
	}
}
