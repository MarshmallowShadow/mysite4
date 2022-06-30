package com.javaex.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;

@Service
public class FileService {
	@Autowired
	private FileDao fDao;
	
	public void save(MultipartFile file) {
		System.out.println("FileService: save");
		
		String orgName = file.getOriginalFilename();
		String saveName = UUID.randomUUID().toString();
		System.out.println(saveName);
	}
}
