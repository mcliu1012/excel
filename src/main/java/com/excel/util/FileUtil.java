package com.excel.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    
    ///////////////////////////方式一////////////////////////////////////////////////////////
    /**
     * 方式一
     * @Description Spring MVC 下载文件 *
     * @param fileName *
     * @param file *
     * @return * @throws IOException
     */
    public static ResponseEntity<byte[]> download(String fileName, File file) throws IOException {
        String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }
    
    ///////////////////////////方式二////////////////////////////////////////////////////////
    
    public static void downloadFile(HttpServletResponse response, File file) throws IOException {
        downloadFile(response, file, "application/x-msdownload;charset=uft-8");
    }

    public static void downloadExcelFile(HttpServletResponse response, File file) throws IOException {
        downloadFile(response, file, "application/vnd.ms-excel;charset=uft-8");
    }

    /**
     * 方式二
     * @param response
     * @param file
     * @param contentType
     * @throws IOException
     */
    public static void downloadFile(HttpServletResponse response, File file, String contentType) throws IOException {
        String fileName = file.getName();
        response.reset();
        HttpServletRequest request = getRequest();
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
    
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
}
