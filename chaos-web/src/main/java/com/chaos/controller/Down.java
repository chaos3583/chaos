package com.chaos.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Down {
	//下载模板
    public static String downloadFile(HttpServletRequest request, HttpServletResponse response){
     String fileName = "入库商品导入模板.xlsx";
        if (fileName != null) {
            InputStream is = request.getServletContext().getResourceAsStream("WEB-INF/xls/" + fileName);
            if (is != null) {
                // 设置强制下载不打开
                response.setContentType("application/force-download");
              
                byte[] buffer = new byte[1024];
                BufferedInputStream bis = null;
                try {
                // 设置文件名
                     response.addHeader("Content-Disposition","attachment;fileName=" +java.net.URLEncoder.encode(fileName, "UTF-8"));
                    bis = new BufferedInputStream(is);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
