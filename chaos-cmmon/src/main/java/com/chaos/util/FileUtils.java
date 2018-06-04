package com.chaos.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	  /**
	   * 文件上传将MultipartFile各式转成File格式
	   * @param file
	   * @return
	   * @throws IOException
	   */
	  public static File multipartToFile(MultipartFile file) throws IOException {  
		  File f = null;
		  try {
		      f=File.createTempFile("tmp", null);
		      file.transferTo(f);
		      f.deleteOnExit();        
		  } catch (Exception e) {
		      e.printStackTrace();
		  }
	      return f;  
	  }  
	  
	  /**
	   * 单张图片上传到指定目录
	   * @param uploadFile
	   * @param uploadDir
	   * @throws Exception
	   */
	  public static void upLoadFile(MultipartFile uploadFile,String uploadDir) throws Exception {
		  File oldFile =multipartToFile(uploadFile);
		  FileInputStream inputStream = new FileInputStream(oldFile);
		  long now = System.currentTimeMillis();
		  File file = new File(uploadDir,now+".jpg");
		  file.createNewFile();
		  FileOutputStream fileOutputStream = new FileOutputStream(file);
		  byte temp[] = new byte[1024];  
	      int size = -1;  
	      while ((size = inputStream.read(temp)) != -1) { // 每次读取1KB，直至读完  
	    	  fileOutputStream.write(temp, 0, size);  
	      } 
		  fileOutputStream.close();  
	      inputStream.close();  
	  }
	  
	  /**
	   * 读取文本文件的内容
	   * @param buffer
	   * @param filePath
	   * @throws IOException
	   */
	  public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
	        InputStream is = new FileInputStream(filePath);
	        byte[] b=new byte[3];  
	        is.read(b);
	        String line; // 用来保存每行读取的内容
	        BufferedReader reader;
	        if(b[0]==-17&&b[1]==-69&&b[2]==-65) {
	        	reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        }else {
	        	reader = new BufferedReader(new InputStreamReader(is, "gb2312"));
	        }
	        line = reader.readLine(); // 读取第一行
	        while (line != null) { // 如果 line 为空说明读完了
	            buffer.append(line); // 将读到的内容添加到 buffer 中
	            buffer.append("\n"); // 添加换行符
	            line = reader.readLine(); // 读取下一行
	        }
	        reader.close();
	        is.close();
	   }
}
