package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	
	public String save(MultipartFile file) {
		System.out.println("FileService: save");
		
		//orgName
		String orgName = file.getOriginalFilename();
		
		//saveName
		String exName = orgName.substring(orgName.lastIndexOf(".")); //파일 형식
		long rand1 = System.currentTimeMillis(); //1차겹침방지
		String rand2 = UUID.randomUUID().toString(); //2차겹침방지
		String saveName = rand1 + rand2 + exName;
		
		//filePath
		String saveDir = "C:\\javaStudy\\upload"; //갤러리 실제 파일 저장할 경로
		String filePath = saveDir + "\\" + saveName;
		
		//fileSize
		long fileSize = file.getSize();
		
		//Vo에 저장
		FileVo fVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println(fVo);
		
		//파일을 filePath경로에 저장
		//폴더 경로는 직점 만들어야함 (자동 생성 안됨)
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(fileData);
			bos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		int count = fDao.insert(fVo);
		System.out.println(count + "건 추가");
		
		return saveName;
	}
}
