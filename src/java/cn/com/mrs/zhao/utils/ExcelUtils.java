package cn.com.mrs.zhao.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import cn.com.mrs.zhao.object.SysUser;

public class ExcelUtils {

	public static void main(String[] args) {
		//声明一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(); 
		
		//生成一个表格
		HSSFSheet sheet = workbook.createSheet("北京工业学院学院名单");
		sheet.setDefaultColumnWidth((short) 7);//设置默认宽度
		sheet.setDefaultRowHeight((short)700);  
		HSSFCellStyle style = workbook.createCellStyle();//标题专用字体设置style  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置字体居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平  
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setTopBorderColor(HSSFColor.BLACK.index);
//		style.setBottomBorderColor(HSSFColor.BLACK.index);
//		style.setLeftBorderColor(HSSFColor.BLACK.index);
//		style.setRightBorderColor(HSSFColor.BLACK.index);
		HSSFCellStyle styleCell = workbook.createCellStyle();
		styleCell.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置字体居中
		styleCell.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直   
		styleCell.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平  
		styleCell.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleCell.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleCell.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleCell.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleCell.setTopBorderColor(HSSFColor.BLACK.index);
		styleCell.setBottomBorderColor(HSSFColor.BLACK.index);
		styleCell.setLeftBorderColor(HSSFColor.BLACK.index);
		styleCell.setRightBorderColor(HSSFColor.BLACK.index);
		//字体设置
		HSSFFont font=workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index //字体颜色
        font.setFontHeightInPoints((short)12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体增粗
        //把字体应用到当前的样式
        style.setFont(font);
		HSSFRow row = sheet.createRow(0);
		row.setRowStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,11)); //标题进行11列合并操作
		HSSFCell yearCell=row.createCell(0);
		yearCell.setCellStyle(style);
		yearCell.setCellValue("北京工业学院成人教育学生成绩单");
		HSSFRow row2 = sheet.createRow(1);
		sheet.addMergedRegion(new Region(1,(short)1,1,(short)2)); 
		sheet.addMergedRegion(new Region(1,(short)3,1,(short)4)); 
		sheet.addMergedRegion(new Region(1,(short)8,1,(short)11)); 
//		row2.setRowStyle(styleCell);
		HSSFCell yearCell2=row2.createCell(0);
		yearCell2.setCellValue("教学站:");
		row2.createCell(1).setCellValue("北京朝阳机械学校");
		row2.createCell(3).setCellValue("年级:");
		row2.createCell(5).setCellValue("2014");
		row2.createCell(7).setCellValue("学期");
		row2.createCell(8).setCellValue("1");
		HSSFRow row3 = sheet.createRow(2);
		sheet.addMergedRegion(new Region(2,(short)1,2,(short)2)); 
		sheet.addMergedRegion(new Region(2,(short)3,2,(short)4)); 
		sheet.addMergedRegion(new Region(2,(short)8,2,(short)11)); 
		HSSFCell yearCell3=row3.createCell(0);
		yearCell3.setCellValue("专业:");
		row3.createCell(1).setCellValue("专升本_会计学");
		row3.createCell(3).setCellValue("学习形式:");
		row3.createCell(5).setCellValue("业余	");
		row3.createCell(7).setCellValue("课程:");
		row3.createCell(8).setCellValue("概率与数理统计");
		HSSFRow row4 = sheet.createRow(3);
		row4.createCell(0).setCellValue("学号");
		row4.createCell(1).setCellValue("姓名");
		row4.createCell(2).setCellValue("平时成绩");
		row4.createCell(3).setCellValue("考试成绩");
		row4.createCell(4).setCellValue("总成绩");
		row4.createCell(5).setCellValue("备注");
		row4.createCell(6).setCellValue("学号");
		row4.createCell(7).setCellValue("姓名");
		row4.createCell(8).setCellValue("平时成绩");
		row4.createCell(9).setCellValue("考试成绩");
		row4.createCell(10).setCellValue("总成绩");
		row4.createCell(11).setCellValue("备注");
		
		
        List<SysUser> list = new ArrayList<SysUser>();
        list.add(new SysUser(20140L,"张三","1"));
        list.add(new SysUser(20141L,"李四","2"));
        list.add(new SysUser(20142L,"王五","3"));
        list.add(new SysUser(20143L,"张三","4"));
        list.add(new SysUser(20144L,"李四","5"));
        list.add(new SysUser(20145L,"王五","6"));
        list.add(new SysUser(20146L,"张三","7"));
        list.add(new SysUser(20147L,"李四","8"));
        list.add(new SysUser(20148L,"王五","9"));
        int num = 0;
        for (short i = 0; i<list.size(); i++) {
            if(i%2==0&&i==0){
            	row = sheet.createRow(i+4);
            	row.createCell(0).setCellValue(list.get(i).getId());
            	row.createCell(1).setCellValue(list.get(i).getUserName());
            	row.createCell(2).setCellValue(list.get(i).getPassword());
            	num = i;
            }else if(i%2==0&&i!=0){
            	row = sheet.createRow(i+4-(i/2));
            	row.createCell(0).setCellValue(list.get(i).getId());
            	row.createCell(1).setCellValue(list.get(i).getUserName());
            	row.createCell(2).setCellValue(list.get(i).getPassword());
            	num = i;
            }else{
            	row.createCell(6).setCellValue(list.get(i).getId());
            	row.createCell(7).setCellValue(list.get(i).getUserName());
            	row.createCell(8).setCellValue(list.get(i).getPassword());
            	num = i;
            }
        }
        HSSFRow numRow = sheet.createRow(num+1);
        sheet.addMergedRegion(new Region(num+1,(short)9,num+1,(short)10)); 
        sheet.addMergedRegion(new Region(num+2,(short)9,num+2,(short)10));
        sheet.addMergedRegion(new Region(num+3,(short)9,num+3,(short)10));
        sheet.addMergedRegion(new Region(num+1,(short)0,num+3,(short)0)); 
        HSSFCell numRowCell=numRow.createCell(0);
        numRowCell.setCellStyle(styleCell);
        numRowCell.setCellValue("成绩分布");
        numRow.createCell(1).setCellValue("分数");
        numRow.createCell(2).setCellValue("90以上");
        numRow.createCell(3).setCellValue("89~80");
        numRow.createCell(4).setCellValue("79~70");
        numRow.createCell(6).setCellValue("69~60");
        numRow.createCell(7).setCellValue("60以下");
        numRow.createCell(8).setCellValue("平均成绩");
        HSSFRow numRow2 = sheet.createRow(num+2);
        numRow2.createCell(1).setCellValue("(人数)");
        numRow2.createCell(2).setCellValue("0");
        numRow2.createCell(3).setCellValue("22");
        numRow2.createCell(4).setCellValue("8");
        numRow2.createCell(6).setCellValue("0");
        numRow2.createCell(7).setCellValue("13");
        numRow2.createCell(8).setCellValue("阅卷老师");
        HSSFRow numRow3 = sheet.createRow(num+3);
        numRow3.createCell(1).setCellValue("(%)");
        numRow3.createCell(2).setCellValue("0.0%");
        numRow3.createCell(3).setCellValue("51.2%");
        numRow3.createCell(4).setCellValue("18.6%");
        numRow3.createCell(6).setCellValue("0.0%");
        numRow3.createCell(7).setCellValue("30.2%");
        numRow3.createCell(8).setCellValue("教务科长");
        try {
        	FileOutputStream out = new FileOutputStream("E://学生表.xls");
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        JOptionPane.showMessageDialog(null, "导出成功!");
	}
}
