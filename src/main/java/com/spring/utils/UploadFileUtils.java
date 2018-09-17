package com.spring.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadFileName = null;
		if(MediaUtils.getMediaType(formatName) != null) { //이미지 파일 여부
			uploadFileName = makeThumbnail(uploadPath, savedPath, savedName); //썸네일
		}else { //이미지 파일이 아닌 경우
			uploadFileName = makeIcon(uploadPath, savedPath, savedName); //결과를 만들어냄
		}
		return uploadFileName;
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	private static String makeThumbnail(String uploadPath, String path, String fileName)  throws Exception{
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	//2018/09/06 형식을 통해서 저장 경로 C:\\zzz\\upload\\2018\\09\\06 폴더를 만든 후 저장
	private static String calcPath(String uploadPath) throws Exception{
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info(datePath);
		return datePath;
	}
	
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(uploadPath + paths[paths.length-1]).exists()) {
			return;
		}
		
		for(String path: paths) {
			File dirPath = new File(uploadPath + path);
			
			if(! dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}

}
