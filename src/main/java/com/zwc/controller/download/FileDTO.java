package com.zwc.controller.download;

/**
 * @author: zhangwch
 * @create: 2020-10-20 11:02
 **/
public class FileDTO {

    private String fileName;

    public FileDTO() {
    }

    public FileDTO(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
