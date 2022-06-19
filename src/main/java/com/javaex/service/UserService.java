package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao uDao;
	
	public int userInsert(UserVo uVo) {
		int count = uDao.userInsert(uVo);
		return count;
	}
	
	public int update(UserVo uVo) {
		int count = uDao.update(uVo);
		return count;
	}
	
	public UserVo getAuthUser(UserVo uVo) {
		UserVo authUser = uDao.getAuthUser(uVo);
		return authUser;
	}
	
	public UserVo getUser(int no) {
		UserVo uVo = uDao.getUser(no);
		return uVo;
	}
}
