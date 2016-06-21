package com.excel.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excel.util.ExcelUtil;
import com.excel.web.entity.Student;

@Controller
@RequestMapping("/user")
public class UserController extends BaseActionController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/exportUser.ajax")
	public void exportUser(HttpServletResponse response) {
		// 生成Excel
        String excelUrl = "E:\\workspace\\excel\\src\\main\\resources" + File.separator + "template" + File.separator;
        String tempUrl = "E:\\workspace\\excel\\src\\main\\resources" + File.separator + "template" + File.separator + "temp" + File.separator;
        if (StringUtils.isBlank(excelUrl) || StringUtils.isBlank(tempUrl)) {
            LOG.error("excel_url获取不到");
            return;
        }
        String templateSrcFilePath = excelUrl + "student.xls";
        String destFilePath = tempUrl + "学生信息.xls";
        
        List<Student> students = new ArrayList<Student>();
        Student student1 = new Student(1L, "章新山", "语文", 95);
        Student student2 = new Student(2L, "章新山", "英语", 94);
        Student student3 = new Student(3L, "章新山", "数学", 93);
        Student student4 = new Student(4L, "邱学义", "语文", 92);
        Student student5 = new Student(5L, "邱学义", "英语", 91);
        Student student6 = new Student(6L, "邱学义", "数学", 90);
        Student student7 = new Student(7L, "李鹏", "语文", 89);
        Student student8 = new Student(8L, "李鹏", "英语", 88);
        Student student9 = new Student(9L, "李鹏", "数学", 87);
        Student student10 = new Student(10L, "任增广", "语文", 86);
        Student student11 = new Student(11L, "任增广", "英语", 85);
        Student student12 = new Student(12L, "任增广", "数学", 84);
        Student student13 = new Student(13L, "张丽", "语文", 83);
        Student student14 = new Student(14L, "张丽", "英语", 82);
        Student student15 = new Student(15L, "张丽", "数学", 81);
        students.add(student1);students.add(student2);students.add(student3);students.add(student4);students.add(student5);
        students.add(student6);students.add(student7);students.add(student8);students.add(student9);students.add(student10);
        students.add(student11);students.add(student12);students.add(student13);students.add(student14);students.add(student15);
        
        Map<String, Object> beanParams = new HashMap<String, Object>();
        beanParams.put("students", students);
        
        ExcelUtil.createExcel(templateSrcFilePath, beanParams, destFilePath);
        try {
            this.downloadExcelFile(response, new File(destFilePath));
        } catch (IOException e) {
            LOG.error("导出报表出错！！！【"+ e.getMessage() +"】", e);
            throw new RuntimeException("导出报表出错！！！【" + e.getMessage() + "】");
        }
	}
}
