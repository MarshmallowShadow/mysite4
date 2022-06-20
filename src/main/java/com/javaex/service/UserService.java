package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao uDao;
	
	public int join(UserVo uVo) {
		int count = uDao.join(uVo);
		return count;
	}
	
	public int update(UserVo uVo) {
		int count = uDao.update(uVo);
		return count;
	}
	
	public UserVo login(UserVo uVo) {
		UserVo authUser = uDao.login(uVo);
		return authUser;
	}
	
	public UserVo getUser(int no) {
		UserVo uVo = uDao.getUser(no);
		return uVo;
	}
}
