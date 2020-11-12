package com.zwc.controller.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author: zhangwch
 * @create: 2020-10-16 23:47
 **/

@RestController
public class UploadController {

    public static void main(String[] args) {
    }

    /**
     * 多个文件上传处理（保存路径为当前项目下）
     *
     * @param params
     * @param file
     * @return
     */
    @RequestMapping("upload")
    public String uploadFile(String params, @RequestParam(name = "file", required = false) MultipartFile[] file) throws IOException {
        BufferedOutputStream out = null;
        System.out.println("======接收报文：\n" + params);
        System.out.println("======文件数：\n" + file.length);
        if (file.length > 0) {
            try {
                // 数组转List
//                List<MultipartFile> mf = Arrays.asList(file);
//                MultipartFile multipartFile = null;
//                for (int i = 0; i < mf.size(); i++) {
//                    multipartFile = mf.get(i);
//                    if (!multipartFile.isEmpty()) {
//                        System.out.println("fileName = [" + multipartFile.getOriginalFilename() + "]");
//                        out = new BufferedOutputStream(
//                                new FileOutputStream(new File(
//                                        multipartFile.getOriginalFilename())));
//                        out.write(multipartFile.getBytes());
//                        out.flush();
//                    }
//                }

                for (int i = 0; i < file.length; i++) {
                    MultipartFile multipartFile = file[i];
                    if (!multipartFile.isEmpty()) {
                        System.out.println("fileName = [" + multipartFile.getOriginalFilename() + "]");
                        out = new BufferedOutputStream(
                                new FileOutputStream(new File(
                                        multipartFile.getOriginalFilename())));
                        out.write(multipartFile.getBytes());
                        out.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } finally {
                if (null != out) {
                    out.close();
                }
            }

        } else {
            System.out.println("==========没有文件=========");
        }
        return "上传成功";
    }

    /**
     * 单个文件上传处理（保存路径为当前项目下）
     *
     * @param params
     * @param file
     * @return
     */
    @RequestMapping("upload1")
    // required = false ：设置非必输
    public String uploadFile(String params, @RequestParam(name = "file", required = false) MultipartFile file) {
        if (!params.isEmpty()) {
            System.out.println("======接收报文：\n" + params);
        } else {
            return "上传失败，因为文件是空的.";
        }

        if (!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                BufferedOutputStream out =
                        new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }

            return "上传成功";

        } else {
            return "上传失败，因为文件是空的.";
        }
    }
}
