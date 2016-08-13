package com.excel.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

/**
 * <b>Title：</b>ExcelUtil.java<br/>
 * <b>Description：</b> excel导入导出工具类<br/>
 */
public class ExcelUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

	/**
	 * 根据模板生成Excel文件.
	 * 
	 * @param templateSrcFilePath 模板文件目录.
	 * @param beanParams 数据
	 * @param destFilePath 生成的文件存放目录
	 */
	public static void createExcel(String templateSrcFilePath, Map<String, Object> beanParams, String destFilePath) {
		// 创建XLSTransformer对象
		XLSTransformer transformer = new XLSTransformer();
		try {
			// 生成Excel文件
			transformer.transformXLS(templateSrcFilePath, beanParams, destFilePath);
		} catch (ParsePropertyException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * 根据配置文件读取Excel文件
	 * 
	 * @param configFilePath 配置文件目录
	 * @param beanParams
	 * @param srcReadFilePath
	 */
	public static void readExcel(String configFilePath, Map<String, Object> beanParams, String srcReadFilePath) {
		InputStream inputXML;
		XLSReader mainReader;
		try {
			inputXML = new BufferedInputStream(new FileInputStream(configFilePath));
			mainReader = ReaderBuilder.buildFromXML(inputXML);
			InputStream inputXLS = new BufferedInputStream(new FileInputStream(srcReadFilePath));
			mainReader.read(inputXLS, beanParams);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}

}
