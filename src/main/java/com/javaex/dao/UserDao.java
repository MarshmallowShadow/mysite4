package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 (아이디, 비밀번호, 이름, 성별 저장된 UserVo필요)
	public int userInsert(UserVo uVo) {
		int count = sqlSession.insert("users.userInsert", uVo);
		return count;
	}
	
	//회원정보 수정 (아이디, 비밀번호, 이름, 성별 저장된 UserVo필요)
	public int update(UserVo uVo) {
		int count = sqlSession.update("users.update", uVo);
		return count;
	}
	
	//회원 탈퇴 (지금은 필요 없음)
	public int delete(int no) {
		int count = sqlSession.delete("users.delete", no);
		return count;
	}
	
	//로그인 (아이디, 비밀번호 들어간 UserVo필수, 회원번호(no), 이름 들어간 authUser 리턴)
	public UserVo login(UserVo uVo) {
		UserVo authUser = sqlSession.selectOne("users.login", uVo);
		return authUser;
	}
	
	//수정 폼 들어가기 전에 필요한 전보 가져오기 (authUser에 있던 no 가져오고 아이디, 비번, 이름, 성별 저장된 UserVo리턴)
	public UserVo getUser(int no) {
		UserVo uVo = sqlSession.selectOne("users.getUser", no);
		return uVo;
	}
}
