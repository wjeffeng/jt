package com.jt.manage.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.facade.manage.service.PicUpLoadService;

@Controller
@RequestMapping("/pic")
public class PicUploadController {
	
	@Autowired
	private PicUpLoadService picUploadService;
	
	
	@ResponseBody
	@RequestMapping("/upload")
	public PicUploadResult upload(MultipartFile uploadFile){
		PicUploadResult result = new PicUploadResult();
		
		String fileName = uploadFile.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf("."));
		boolean isPic = fileName.matches("^.*?\\.(jpg|jpeg|png|gif)$");
		if(!isPic){
			result.setError(1);
		}else{
			try {
				BufferedImage image = ImageIO.read(uploadFile.getInputStream());
				result.setWidth(""+image.getWidth());
				result.setHeight(""+image.getHeight());
				
				String newFileName = ""+System.currentTimeMillis()+RandomUtils.nextInt(100,999)+extName;
				String _dir = "/images/"+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/";
				String path =picUploadService.UPLOAD_DIR+_dir;
				String url = picUploadService.IMAGE_BASE_URL+ _dir + newFileName;
				result.setUrl(url);
			
				File dir = new File(path);
				if(!dir.exists()){
					dir.mkdirs();
				}
				
				uploadFile.transferTo(new File(path+newFileName));
			} catch (IOException e) {
				result.setError(1);
				e.printStackTrace();
			}
		}
		return result; 
	}
}
