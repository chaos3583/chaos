package com.chaos.demo.littleDemo;

import java.io.File;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Demo2 {
	public static void main(String[] args) {
		FileConvert("C:\\Users\\Administrator\\Desktop\\feign.doc","E:\\ftpUpload","pdf");
	}
	
	public static void FileConvert(String inputFile,String dirPath,String type) {
		File outputFile = null;
		if("html".equals(type)) {
			outputFile = new File(dirPath + "/" + new Date().getTime()
				        + ".html");
		}else if("pdf".equals(type)) {
			outputFile = new File(dirPath + "/" + new Date().getTime()
				        + ".pdf");
		}
		 OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		   try{
		   connection.connect();
		   }catch(Exception e){
		    e.printStackTrace();
		   }
		   DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		   converter.convert(new File(inputFile), outputFile);
		   connection.disconnect();
		   
	}
}
