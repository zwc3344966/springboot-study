package com.zwc.controller.download;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: zhangwch
 * @create: 2020-10-20 11:25
 **/
public class DownloadTest {

    public static void main(String[] args) throws IOException {
        String url = "http://127.0.0.1:8080/download";
        String fileName = "155.pdf";
        String localPath = System.getProperty("user.dir") + "\\" + fileName;
        downloadFile1(fileName, url, localPath);
    }

    /**
     * 下载文件
     * @param fileName 文件名
     * @param downloadUrl 文件下载地址
     * @param savePath 本地文件保存地址
     * @throws IOException
     */
    public static void downloadFile1(String fileName, String downloadUrl, String savePath) throws IOException {
        File localpath = new File(savePath);
        // 获取文件的上一层目录
        File parentFile = localpath.getParentFile();
        // 判断目录是否存在，不存在则创建
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        // 还可以是用 HttpURLConnection
        URLConnection conn = null;
        URL url = null;
        InputStream in = null;
        FileOutputStream out = null;
        try {
            url = new URL(downloadUrl);
            conn = url.openConnection();
            // 发送Post请求(设置以下两个属性后才可以使用：conn.getInputStream().read()；conn.getOutputStream().write() )
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // 设置请求头参数（此处为示例，可更换，不需要可删除）
            conn.setRequestProperty("User-Agent", "PostmanRuntime/7.26.5");
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("fileName", fileName);

            // 设置请求超时为15s
            conn.setConnectTimeout(15 * 1000);
            in = conn.getInputStream();
            byte[] bytes = readInputStream(in);
            out = new FileOutputStream(localpath);
            out.write(bytes);
        } catch (IOException e) {
            System.out.println("文件IO异常："+ e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("文件下载异常："+ e.getMessage());
            throw e;
        } finally {
            // 先开后关
            if (null != out) {
                out.close();
            }
            if (null != in) {
                in.close();
            }
        }
    }

    /**
     * 下载文件（使用了BufferedOutputStream():带缓冲区的输出流）
     * @param downloadUrl 文件下载地址
     * @param savePath 本地文件保存地址
     */
    public static void downloadFile2(String downloadUrl, String savePath) throws IOException {
        File loccalpath = new File(savePath);
        // 获取文件的上一层目录
        File parentFile = loccalpath.getParentFile();
        // 判断目录是否存在，不存在则创建
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        // 还可以是用 HttpURLConnection
        URLConnection conn = null;
        URL url = null;
        InputStream in = null;
        FileOutputStream out = null;
        BufferedOutputStream bfOut = null;
        try {
            url = new URL(downloadUrl);
            conn = url.openConnection();
            // 发送Post请求(设置以下两个属性后才可以使用：conn.getInputStream().read()；conn.getOutputStream().write() )
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // 设置请求头参数（此处为示例，可更换，不需要可删除）
            conn.setRequestProperty("User-Agent", "PostmanRuntime/7.26.5");
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            conn.setRequestProperty("Connection", "keep-alive");
            //设置请求超时为15s
            conn.setConnectTimeout(15 * 1000);
            in = conn.getInputStream();
            byte[] bytes = readInputStream(in);
            out = new FileOutputStream(loccalpath);
            bfOut = new BufferedOutputStream(out);
            bfOut.write(bytes);
            /**
             * 字节流默认是不需要使用缓存的，BufferedOutputStream是使用了缓存的字节流，需要调用flush()
             * flush()：刷新此输出流并强制任何缓冲流和输出字节被写出
             */
            bfOut.flush();
        } catch (IOException e) {
            System.out.println("文件IO异常："+ e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("文件下载异常："+ e.getMessage());
            throw e;
        } finally {
            // 先开后关,后开先关
            if (bfOut != null) {
                bfOut.close();
            }
            if (null != out) {
                out.close();
            }
            if (null != in) {
                in.close();
            }
        }
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            System.out.println(String.format("IO异常：%s", e.getMessage()));
            throw e;
        } finally {
            if (null != bos) {
                bos.close();
            }
        }
        return bos.toByteArray();
    }
}
