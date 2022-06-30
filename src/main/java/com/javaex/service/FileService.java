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
		String exName = orgName.substring(orgName.lastIndexOf(".")); //파일 형식
		String rand1 = UUID.randomUUID().toString(); //1차겹침방지
		long rand2 = System.currentTimeMillis(); //2차겹침방지
		String saveName = rand1 + rand2 + exName;
		System.out.println(saveName);
		
		String saveDir = "C:\\javastudy\\fileupload"; //갤러리 실제 파일 경로
		String filePath = saveDir + "\\" + saveName;
		
	}
}
