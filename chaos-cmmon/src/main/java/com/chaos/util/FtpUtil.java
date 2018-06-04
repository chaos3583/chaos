package com.chaos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;


public class FtpUtil {
	private  Log log = LogFactory.getLog(getClass()) ;
	
	private String userName; 
	private String password;
	private String ip; 
	private int port; 
	
	private FTPClient ftpClient = null; 
	private FTPSClient ftps = null ;
	
	//构造方法初始化类
	public FtpUtil(String userName, String password, String ip, int port) {
		this.userName = userName;
		this.password = password;
		this.ip = ip;
		this.port = port;
	}
        //连接ftp
	public boolean connectServer() throws Exception{
		boolean flag = true;
		if (ftpClient == null) { 
			ftpClient = new FTPClient();
			ftpClient.connect(ip,port);
			
			log.info("Connected to " + ip);
			log.info(ftpClient.getReplyString());
			
			int reply = ftpClient.getReplyCode(); 
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				log.warn("FTP server refused connection.");
				return false ;
			}
			
			boolean bok = ftpClient.login(userName, password);
			if (!bok)  {
				try {
					ftpClient.disconnect() ;
					ftpClient = null ;
				} catch (Exception e) { }
				throw new Exception("can not login ftp server") ;
			}
			
			ftpClient.setBufferSize(1024);
			ftpClient.setControlEncoding("GBK"); 
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
			ftpClient.setDataTimeout(120000); 
			ftpClient.enterLocalPassiveMode();
			ftpClient.setUseEPSVwithIPv4(false);
		} 
		return flag;
	}
        //列出所有文件内容
	public List<String> listRemoteAllFiles(String path) throws Exception {	
		ftpClient.enterLocalPassiveMode(); 
		FTPFile[] files = ftpClient.listFiles(path, new FTPFileFilter() { 
			@Override
			public boolean accept(FTPFile file) { 
				if (file.isFile()) return true ;
				return false ;
			}}) ;
		
		List<String> list = new ArrayList() ;
		for (FTPFile file : files) {
			list.add(file.getName()) ;
		}
		return list ;
	}

	public void closeConnect() { 
		try {
			if (ftpClient != null) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (Exception e) {
		}

	}
        //下载文件
	public boolean downloadFile(String remotePath, String fileName, String localPath) throws Exception {
		
		FileOutputStream fos = null ; 
		try {
			File localFile = new File(localPath, fileName);
			fos = new FileOutputStream(localFile);
			
			ftpClient.enterLocalPassiveMode(); 
			ftpClient.changeWorkingDirectory(remotePath) ;
			boolean bok = ftpClient.retrieveFile(fileName, fos);
			
			fos.close() ;
			fos = null ;
			
			return bok ;
		} catch (Exception e) {
			throw e ;
		}
		finally {
			if (fos!=null) {
				try {
					fos.close() ;
					fos = null ;
				} catch (Exception e2) { }
			}
		} 
		
	}
        //上传文件
	public boolean uploadFile(String remotePath, String filename, String localFilePath) throws Exception {
		FileInputStream fis = null ;
		try {
			fis = new FileInputStream(new File(localFilePath));
			
			ftpClient.enterLocalPassiveMode(); 
			ftpClient.changeWorkingDirectory(remotePath);
			boolean bok = ftpClient.storeFile(filename, fis); 
			
			fis.close();
			fis = null ;
			
			return bok ;
		} catch (Exception e) {
			throw e ;
		}
		finally {
			if (fis!=null) {
				try {
					fis.close() ;
					fis = null ;
				} catch (Exception e2) { }
			}
		}

	}
	//删除文件
	public boolean removeFile(String remotePath, String filename) throws Exception {
		ftpClient.changeWorkingDirectory(remotePath);
		boolean bok = ftpClient.deleteFile(filename) ; 
		return bok ;
	}

}