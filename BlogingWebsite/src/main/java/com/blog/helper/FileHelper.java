package com.blog.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class FileHelper {
	
//	public static String filepath;
	
	public static String generateRandomFileName(String originalFileName) {
        String fileExtension = "";
        int lastIndex = originalFileName.lastIndexOf('.');
        if (lastIndex > 0) {
            fileExtension = originalFileName.substring(lastIndex);
        }
        String randomUUID = UUID.randomUUID().toString();
        String randomFileName = randomUUID + fileExtension;

        return randomFileName;
    }
	
	public static boolean  uploadfile(MultipartFile file,String filename) {
		 boolean flag = false;
		 try { 
	      String  UPLOAD_DIR = new ClassPathResource("/static/images").getFile().getAbsolutePath();
		  Files.copy(file.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+filename)
				,StandardCopyOption.REPLACE_EXISTING);	
		  
		   System.out.println("upload directory path  :"+UPLOAD_DIR);
		 flag = true; 
		
		 } catch (IOException e) {
			
			e.printStackTrace();
		}
		 return flag;
	 }
	
	
	public static String getImagePath(String filename) {
		System.getProperty("user.dir");
		   return "";
	}
//	
//	
//	public static String getFileUrlByName(String fileName) {	
//		 String s = (ServletUriComponentsBuilder.fromCurrentContextPath().path("images/").path(fileName)).toUriString();
//		 System.out.println("s : "+s);
//		 return s;
//	 }
//	  

}
