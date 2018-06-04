package com.chaos;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chaos.util.FileUtils;


@RestController
@RequestMapping("/chaos")
public class ChaosController {

  @RequestMapping("/home")
  public ModelAndView home() throws Exception{
	 ModelAndView model = new ModelAndView();
  	model.setViewName("views/ftpUpload");
  	return model;
  }
  
  /**
   * 单张图片上传
   * @param uploadFile
   * @param request
   * @param response
   * @throws IOException
   */
  @RequestMapping("/upload")
  public void upload(MultipartFile uploadFile,HttpServletRequest request,HttpServletResponse response) throws IOException{
	 String uploadDir = "E:/ftpUpload";
	 try {
		FileUtils.upLoadFile(uploadFile, uploadDir);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  /**
   * 读取文本文件内容
   * @param uploadFile
   * @param request
   * @param response
   * @throws IOException
 * @throws ParserConfigurationException 
 * @throws TransformerException 
   */
  @RequestMapping("/uploadText")
  public void uploadText(MultipartFile uploadFile,HttpServletRequest request,HttpServletResponse response) throws IOException, TransformerException, ParserConfigurationException{
	  File oldFile =FileUtils.multipartToFile(uploadFile);
	  StringBuffer sb = new StringBuffer();
	  FileUtils.readToBuffer(sb, oldFile.getAbsolutePath());
	  String text = sb.toString();
	  
	  System.out.println(text);
  }
  

}
