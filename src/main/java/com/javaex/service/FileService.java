package com.javaex.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;
import com.javaex.vo.FileVo;

@Service
public class FileService {
	@Autowired
	private FileDao fDao;
	
	public void save(MultipartFile file) {
		System.out.println("FileService: save");
		
		//orgName
		String orgName = file.getOriginalFilename();
		
		//saveName
		String exName = orgName.substring(orgName.lastIndexOf(".")); //파일 형식
		String rand1 = UUID.randomUUID().toString(); //1차겹침방지
		long rand2 = System.currentTimeMillis(); //2차겹침방지
		String saveName = rand1 + rand2 + exName;
		
		//filePath
		String saveDir = "C:\\javastudy\\fileupload"; //갤러리 실제 파일 경로
		String filePath = saveDir + "\\" + saveName;
		
		//fileSize
		long fileSize = file.getSize();
		
		//Vo에 저장
		FileVo fVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println(fVo);
		
		
	}
}
