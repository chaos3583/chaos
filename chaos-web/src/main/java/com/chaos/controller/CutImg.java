package com.chaos.controller;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FilenameUtils;  
  
public class CutImg {  
      
      
    private static final String JPG_HEX = "ff";  
    private static final String PNG_HEX = "89";  
    private static final String JPG_EXT = "jpg";  
    private static final String PNG_EXT = "png";  
    public static String getFileExt(String filePath) {  
        FileInputStream fis = null;  
        String extension = FilenameUtils.getExtension(filePath);  
        try {  
            fis = new FileInputStream(new File(filePath));  
            byte[] bs = new byte[1];  
            fis.read(bs);  
            String type = Integer.toHexString(bs[0]&0xFF);  
            if(JPG_HEX.equals(type))  
                extension = JPG_EXT;  
            if(PNG_HEX.equals(type))  
                extension = PNG_EXT;  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if(fis != null)  
                    fis.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return extension;  
    }  
      
     public static boolean cutPic(String srcFile, String outFile, int x, int y,    
                int width, int height,String fileName) {    
            FileInputStream is = null;    
            ImageInputStream iis = null;    
            try {    
                // 如果源图片不存在    
                if (!new File(srcFile).exists()) {    
                    return false;    
                }    
        
                // 读取图片文件    
                is = new FileInputStream(srcFile);   
                  
                  
                  
        
                // 获取文件格式    
                  
                String ext =getFileExt(srcFile);  
                //String ext = fileName.substring(fileName.lastIndexOf(".") + 1);    
        
                // ImageReader声称能够解码指定格式    
                Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);    
                ImageReader reader = it.next();    
        
                // 获取图片流    
                iis = ImageIO.createImageInputStream(is);    
        
                // 输入源中的图像将只按顺序读取    
                reader.setInput(iis, true);    
        
                // 描述如何对流进行解码    
                ImageReadParam param = reader.getDefaultReadParam();    
        
                // 图片裁剪区域    
                Rectangle rect = new Rectangle(x, y, width, height);    
        
                // 提供一个 BufferedImage，将其用作解码像素数据的目标    
                param.setSourceRegion(rect);    
        
                // 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象    
                BufferedImage bi = reader.read(0, param);    
                  
                  
                  
                File tempOutFile = new File(outFile, fileName);  
                File tempOutFileDir = new File(outFile);  
                if (!tempOutFileDir.exists()) {  
                    tempOutFileDir.mkdirs();  
                }  
                tempOutFile.createNewFile();  
                ImageIO.write(bi, ext, tempOutFile);    
                return true;    
            } catch (Exception e) {    
                e.printStackTrace();    
                return false;    
            } finally {    
                try {    
                    if (is != null) {    
                        is.close();    
                    }    
                    if (iis != null) {    
                        iis.close();    
                    }    
                } catch (IOException e) {    
                    e.printStackTrace();    
                    return false;    
                }    
            }    
        }    
}  
