package com.hbut.sys.entity;

/**
 * @author : zhangyf
 * @project: com.hbut.sys.entity
 * @date: 2023/6/26 15:05
 */
public class FileUploadResponse {
    //文件路径
    private String path;
    //文件名
    private String fileName;


    public FileUploadResponse() {
    }

    public FileUploadResponse(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    /**
     * 获取
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String toString() {
        return "FileUploadResponse{path = " + path + ", fileName = " + fileName + "}";
    }
}
