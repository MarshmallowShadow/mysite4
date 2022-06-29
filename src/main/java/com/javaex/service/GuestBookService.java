package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookDao gDao;
	
	public List<GuestVo> getList(){
		List<GuestVo> gList = gDao.getList();
		return gList;
	}
	public int delete(GuestVo gVo) {
		int count = gDao.delete(gVo);
		return count;
	}
	//ajax insert
	public GuestVo ajaxInsert(GuestVo guestVo) {
		int count = gDao.insert(guestVo);
		GuestVo gVo = null;
		if(count > 0) {
			gVo = gDao.getGuest(guestVo.getNo());
		}
		return gVo;
	}
	//일반 insert
	public int insert(GuestVo guestVo) {
		int count = gDao.insert(guestVo);
		return count;
	}
}
