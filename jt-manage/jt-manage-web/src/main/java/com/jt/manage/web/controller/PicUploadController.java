package com.jt.manage.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@Controller
@RequestMapping("/pic")
public class PicUploadController {
	
	@RequestMapping("/upload")
	public PicUploadResult upload(MultipartFile uploadFile){
		PicUploadResult result = new PicUploadResult();
		
		String fileName = uploadFile.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf("."));
		boolean isPic = fileName.matches("^.*\\.(jpg|jpeg|png|gif)$");
		if(!isPic){
			result.setStatus(1);
		}else{
			try {
				BufferedImage image = ImageIO.read(uploadFile.getInputStream());
				result.setWidth(""+image.getWidth());
				result.setHeight(""+image.getHeight());
				
				String newFileName = ""+System.currentTimeMillis()+RandomUtils.nextInt(100,999);
				String _dir = "/images/"+new SimpleDateFormat("yyyy/MM/dd").format(new Date());
				String path = "F:/Github/uploadDir";
				
				String url = "http://image.jt.com" + _dir + newFileName;
				
				File dir = new File(path+_dir);
				if(!dir.exists()){
					dir.mkdirs();
				}
				
				uploadFile.transferTo(new File(path+_dir+newFileName));
			} catch (IOException e) {
				result.setStatus(1);
				e.printStackTrace();
			}
		}
		return result; 
	}
}
