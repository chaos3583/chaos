package com.chaos.util;
import java.io.File;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 将Word文档转换成html字符串的工具类
 * 
 * @author MZULE
 * 
 */
public class FileConvertUtils {
    /**
	 * 
	 * @param inputFile 源文件路径 word格式
	 * @param dirPath	目标文件夹路径
	 * @param type		目标文件类型，支持html、pdf
	 */
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
