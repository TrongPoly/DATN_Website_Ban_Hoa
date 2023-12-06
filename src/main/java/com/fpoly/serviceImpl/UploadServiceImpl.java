package com.fpoly.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fpoly.service.UploadService;

import jakarta.servlet.ServletContext;

@Service
public class UploadServiceImpl implements UploadService{
	
	@Autowired
	ServletContext app;
	
	public File save(MultipartFile file,String folder) {
			
			File dir = new File(app.getRealPath("/" + folder));
			
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String filename = file.getOriginalFilename();
			File savedFile = new File(dir.getAbsolutePath() + File.separator + filename);

			// Lưu tệp vào thư mục
			
			try {	
			file.transferTo(savedFile);	
			System.out.println(savedFile.getAbsolutePath());
			return savedFile;
		} catch (IOException e) {
			throw new RuntimeException("Lỗi lưu tệp", e); // Ném lỗi nếu xảy ra lỗi khi lưu tệp
		}
	}
	}
	

