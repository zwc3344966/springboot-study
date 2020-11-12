package com.zwc.controller.download;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author: zhangwch
 * @create: 2020-10-20 10:17
 **/
@RestController
public class DownloadController {

    public static void main(String[] args) {
    }

    /**
     * @RequestBody String str : 接收单个参数（接收JSON字符串后可格式化为JSON对象解析）
     * @RequestBody FileDTO fileDTO : 接收JSON参数
     */
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws IOException {
        FileInputStream in = null;
        BufferedInputStream bfIn = null;
        ServletOutputStream outputStream = null;
        File file = null;
        try {
//            String filePath = System.getProperty("user.dir") + "\\" + "1111.txt";
            String filePath = System.getProperty("user.dir") + "\\" + "be92cf16-4f6e-44d8-82f3-0a2e9ddd6b55.png";
            System.out.println("filePath = [" + filePath + "]");
            file = new File(filePath);
            in = new FileInputStream(file);
            bfIn = new BufferedInputStream(in);
            // 图片
            response.setContentType("image/jpeg");
            // pdf文件
//            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filePath.substring(0, filePath.lastIndexOf(".")), "utf-8") + filePath.substring(filePath.lastIndexOf(".")));
            outputStream = response.getOutputStream();
            byte[] buff = new byte[1024];
            int len;
            while ((len = bfIn.read(buff)) != -1) {
                outputStream.write(buff, 0 , buff.length);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (bfIn != null) {
                bfIn.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 从请求头（HttpHeaders）中获取文件名（单个文件）
     * @param response 响应对象
     * @param headers 请求头
     * @throws IOException
     */
    @RequestMapping(value = "download2", method = RequestMethod.POST)
    public void download(HttpServletResponse response, @RequestHeader HttpHeaders headers) throws IOException {
        FileInputStream in = null;
        BufferedInputStream bfIn = null;
        ServletOutputStream outputStream = null;
        File file = null;
        try {
            System.out.println("headers = [" + headers.size() + "]");
            // 从请求头获取文件名
            List<String> fileNameList = headers.get("fileName");
            String fileName = fileNameList.get(0);
            System.out.println("fileName = [" + fileName + "]");
            String filePath = System.getProperty("user.dir") + "\\" + fileName;
            System.out.println("filePath = [" + filePath + "]");
            file = new File(filePath);
            in = new FileInputStream(file);
            bfIn = new BufferedInputStream(in);
            // 图片
//            response.setContentType("image/jpeg");
            // pdf文件
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName.substring(0, fileName.indexOf(".")), "utf-8") + filePath.substring(filePath.lastIndexOf(".")));
            outputStream = response.getOutputStream();
            byte[] buff = new byte[1024];
            int len;
            while ((len = bfIn.read(buff)) != -1) {
                outputStream.write(buff, 0 , buff.length);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (bfIn != null) {
                bfIn.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }
}
