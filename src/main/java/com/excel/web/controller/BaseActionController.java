package com.excel.web.controller;

import java.beans.PropertyEditorSupport;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.excel.util.DateUtil;

import net.sf.json.JSONObject;

public class BaseActionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseActionController.class);

	protected static final String UTF8 = "UTF-8";

//	protected Map<String, Object> page(Page<?> dataPage) {
//		Map modelMap = new HashMap(3);
//		modelMap.put("records", Integer.valueOf(dataPage.getTotal()));
//		modelMap.put("rows", dataPage.getDatas());
//		modelMap.put("page", dataPage.getPage());
//		modelMap.put("total", Integer.valueOf(dataPage.getTotalPage()));
//		modelMap.put("success", Boolean.valueOf(true));
//		return modelMap;
//	}

	protected <T> Map<String, Object> success(T data) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", data);
		modelMap.put("success", Boolean.valueOf(true));
		return modelMap;
	}

	protected Map<String, Object> fail(String message) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("message", message);
		modelMap.put("success", Boolean.valueOf(false));
		return modelMap;
	}

	protected void fail(HttpServletResponse response, String message) throws IOException {
		outputString(response, JSONObject.fromObject(fail(message)).toString());
	}

	protected <T> void success(HttpServletResponse response, T message) throws IOException {
		outputString(response, JSONObject.fromObject(success(message)).toString());
	}

	protected void output(HttpServletResponse response, String contentType, String characterEncoding, String obj) throws IOException {
		disableCache(response);
		response.setContentType(contentType);
		response.setCharacterEncoding(characterEncoding);
		response.getWriter().write(obj);
		response.getWriter().flush();
		response.getWriter().close();
	}

	protected void disableCache(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0L);
		response.setHeader("Cache-Control", "no-cache");
	}

	protected void outputMatch(HttpServletRequest request, HttpServletResponse response, String obj) throws IOException {
		String requested = request.getHeader("x-requested-with");
		if (StringUtils.isEmpty(requested))
			requested = request.getHeader("X-Requested-With");
		if ((requested != null) && (requested.equalsIgnoreCase("XMLHttpRequest"))) {
			output(response, "application/json", "UTF-8", obj);
		} else {
			String rtnName = request.getParameter("rtnName");
			if (isNotEmpty(rtnName))
				outputJS(request, response, "var " + rtnName + "=" + obj);
			else
				output(response, "text/html", "UTF-8", obj);
		}
	}

	protected void outputString(HttpServletResponse response, String obj) throws IOException {
		output(response, "text/html", "UTF-8", obj);
	}

	protected void outputJSON(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
		output(response, "application/json", "UTF-8", msg);
	}

	protected void outputJS(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
		output(response, "application/javascript", "UTF-8", msg);
	}

	protected Map<String, Object> fail(String code, String message) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("code", code);
		modelMap.put("message", message);
		modelMap.put("success", Boolean.valueOf(false));
		return modelMap;
	}

	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	protected HttpSession getSession() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (sra != null)
			return sra.getRequest().getSession();
		return null;
	}

	protected boolean isEmpty(String value) {
		return (value == null) || ("".equals(value.trim()));
	}

	protected boolean isEmpty(Long value) {
		return value == null;
	}

	protected boolean isNotEmpty(String value) {
		return (value != null) && (!"".equals(value.trim()));
	}

	protected boolean isNotEmpty(Long value) {
		return value != null;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			public void setAsText(String text) {
				setValue(text == null ? null : text.trim());
			}

			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String text) {
				if (BaseActionController.this.isNotEmpty(text))
					if (text.length() == 10)
						setValue(DateUtil.getDate(text, "yyyy-MM-dd"));
					else if (text.length() == 19)
						setValue(DateUtil.getDate(text, "yyyy-MM-dd HH:mm:ss"));
			}
		});
	}

	protected void downloadFile(HttpServletResponse response, File file) throws IOException {
		this.downloadFile(response, file, "application/x-msdownload;charset=uft-8");
	}

	protected void downloadExcelFile(HttpServletResponse response, File file) throws IOException {
		this.downloadFile(response, file, "application/vnd.ms-excel;charset=uft-8");
	}

	protected void downloadFile(HttpServletResponse response, File file, String contentType) throws IOException {
		String fileName = file.getName();
		response.reset();
		HttpServletRequest request = this.getRequest();
		response.setContentType(contentType);
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE")) {// IE
			// 设置文件头，文件名称或编码格式
			response.addHeader("Content-Disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");
		} else {// firefox
			response.addHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
		}

		OutputStream myout = null;
		FileInputStream fis = null;
		try {
			// 读出文件到i/o流
			fis = new FileInputStream(file);
			BufferedInputStream buff = new BufferedInputStream(fis);
			byte[] b = new byte[1024];// 相当于我们的缓存
			long k = 0;// 该值用于计算当前实际下载了多少字节
			// 从response对象中得到输出流,准备下载
			myout = response.getOutputStream();
			// 开始循环下载
			while (k < file.length()) {
				int j = buff.read(b, 0, 1024);
				k += j;
				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);
			}
			myout.flush();
			if (buff != null) {
				buff.close();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(myout);
			IOUtils.closeQuietly(fis);
		}
	}

}
