package com.chaos.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.chaos.model.Member;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelImportDemo {

	public static void main(String[] args) {
		String file = "C:\\Users\\Administrator\\Desktop\\member.xls";
		getAllByExcel(file);
	}
	/**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
    public static List<Member> getAllByExcel(String file){
        List<Member> list=new ArrayList<Member>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            System.out.println(clos+" rows:"+rows);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String employeeId=rs.getCell(j, i).getContents();
                    String gender=rs.getCell(j++, i).getContents();
                    String nickname=rs.getCell(j++, i).getContents();
                    String uname=rs.getCell(j++, i).getContents();
                    String passwd=rs.getCell(j++, i).getContents();
                    String email=rs.getCell(j++, i).getContents();
                    String phone=rs.getCell(j++, i).getContents();
                    String regTime=rs.getCell(j++, i).getContents();
                    String lastLogin=rs.getCell(j++, i).getContents();
                    String lastIp=rs.getCell(j++, i).getContents();
                    System.out.println(j+"========"+"id:"+id+" employeeId:"+employeeId+" gender:"+gender+" nickname:"+nickname+" uname:"+uname+" passwd:"+passwd+" email:"+email+" phone:"+phone+" regTime:"+regTime+" lastLogin:"+lastLogin+" lastIp:"+lastIp);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
        
    }
}
