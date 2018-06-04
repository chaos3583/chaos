package com.chaos.excel;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommonExcelRead {
	private static Logger LOGGER = LoggerFactory.getLogger(CommonExcelRead.class);
	public static Map<String, Object> readExcel(String fileName,InputStream inputStream) throws IOException {
		Map<String, Object> excelMap = null;
		if (fileName == null || ExcelCommon.EMPTY.equals(fileName)) {
			excelMap = new HashMap<String,Object>();
			excelMap.put("readStatus", "error");
			excelMap.put("errorMsg", "无法读取Excel文件内容");
			return excelMap;
		} else {
			String postfix = getPostfix(fileName);
			if (!ExcelCommon.EMPTY.equals(postfix) && (ExcelCommon.OFFICE_EXCEL_2003_POSTFIX.equals(postfix) 
					|| ExcelCommon.OFFICE_EXCEL_2010_POSTFIX.equals(postfix))) {
				if (ExcelCommon.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					excelMap = readXls(inputStream);
				} else if (ExcelCommon.OFFICE_EXCEL_2010_POSTFIX
						.equals(postfix)) {
					excelMap = readXlsx(inputStream);
				}
			} else {
				//Annotation		
				LOGGER.error(fileName + ExcelCommon.NOT_EXCEL_FILE);
				excelMap = new HashMap<String,Object>();
				excelMap.put("readStatus", "error");
				excelMap.put("errorMsg", "请选择Excel文件上传");
				return excelMap;
			}
		}
		return excelMap;
	}
	
	public static String getPostfix(String fileName) {
		if (fileName == null || ExcelCommon.EMPTY.equals(fileName.trim())) {
			return ExcelCommon.EMPTY;
		}
		if (fileName.contains(ExcelCommon.POINT)) {
			return fileName.substring(fileName.lastIndexOf(ExcelCommon.POINT) + 1,
					fileName.length());
		}
		return ExcelCommon.EMPTY;
	}
	
	public static Map<String, Object> readXls(InputStream inputStream){
		//Annotation	
		LOGGER.info("Read the Excel 2003-2007");
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		Map<String, Object> excelMap = new HashMap<String, Object>();
		HSSFWorkbook wb=null;
		try {
			wb = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			excelMap.put("readStatus", "error");
			excelMap.put("errorMsg", "无法读取Excel文件内容");
			e.printStackTrace();
			return excelMap;
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		Object val = null;
		DecimalFormat df = new DecimalFormat("#");// 格式化数字
		try {
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					dataList.add(null);
					continue;
				}
				List<Object> objList = new ArrayList<Object>();
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						val = null;
						objList.add(val);
						continue;
					}
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						val = cell.getStringCellValue();
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
						val= df.format(bd);
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						val = cell.getBooleanCellValue();
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						val = null;
						break;
					default:
						val = cell.toString();
						break;
					}
					objList.add(val);
				}
				dataList.add(objList);
			}
			excelMap.put("readStatus", "pass");
			excelMap.put("dataList", dataList);
		} catch (Exception e) {
			e.printStackTrace();
			excelMap.put("readStatus", "error");
			excelMap.put("errorMsg", "导入出错,请联系管理员");
		}
		return excelMap;
	}
	
	public static Map<String, Object> readXlsx(InputStream inputStream){
		//Annotation	
		LOGGER.info("Read the Excel 2010");
		Map<String, Object> excelMap = new HashMap<String, Object>();
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		XSSFWorkbook xssfWorkbook = null;
		try {
			xssfWorkbook = new XSSFWorkbook(inputStream);
		}catch (IOException e) {
			excelMap.put("readStatus", "error");
			excelMap.put("errorMsg", "无法读取Excel文件内容");
			e.printStackTrace();
			return excelMap;
		}
		XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
		XSSFRow row = null;
		XSSFCell cell = null;
		Object val = null;
		DecimalFormat df = new DecimalFormat("#");// 格式化数字
		try {
			for (int i = sheet.getFirstRowNum(); i < sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					dataList.add(null);
					continue;
				}
				List<Object> objList = new ArrayList<Object>();
				for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						val = null;
						objList.add(val);
						continue;
					}
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						val = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						val= df.format(cell.getNumericCellValue());
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						val = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						val = null;
						break;
					default:
						val = cell.toString();
						break;
					}
					objList.add(val);
				}
				dataList.add(objList);
			}
			excelMap.put("readStatus", "pass");
			excelMap.put("dataList", dataList);
		} catch (Exception e) {
			e.printStackTrace();
			excelMap.put("readStatus", "error");
			excelMap.put("errorMsg", "导入出错,请联系管理员");
		}
		return excelMap;
	}

    public static int getRowCount(String fileName, InputStream inputStream) {
        String postfix = getPostfix(fileName);
        try {
            if (!ExcelCommon.EMPTY.equals(postfix)
                    && (ExcelCommon.OFFICE_EXCEL_2003_POSTFIX.equals(postfix) || ExcelCommon.OFFICE_EXCEL_2010_POSTFIX
                            .equals(postfix))) {
                if (ExcelCommon.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return (new HSSFWorkbook(inputStream).getSheetAt(0)
                            .getPhysicalNumberOfRows());
                } else if (ExcelCommon.OFFICE_EXCEL_2010_POSTFIX
                        .equals(postfix)) {
                    return (new XSSFWorkbook(inputStream).getSheetAt(0)
                            .getPhysicalNumberOfRows());
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (IOException e) {
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
