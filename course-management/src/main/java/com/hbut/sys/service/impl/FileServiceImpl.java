package com.hbut.sys.service.impl;

import com.hbut.common.vo.Result;
import com.hbut.sys.entity.FileUploadResponse;
import com.hbut.sys.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zhangyf
 * @project: com.hbut.sys.service.impl
 * @date: 2023/6/26 11:40
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    //拦截的url，虚拟路径
    public String pathPattern = "files";
    
    //自己设置的目录
    private static final String fileDir = "fileStorage";

    //上传文件存放目录  =  工作目录绝对路径 + 自己设置的目录，也可以直接自己指定服务器目录
    //windows本地测试
    //绝对路径: D:\develop\work\project\myblog\myblog-file-upload\fileStorage\202302021010345680.jpg
    //System.getProperty("user.dir")   D:\develop\work\project\myblog\myblog-file-upload
    //fileDir                          fileStorage
    //fileName                         202302021010345680.jpg
    public String filePath = System.getProperty("user.dir") + File.separator + fileDir + File.separator;
    // @Value("${file.upload.idr}")
    // public String filePath;
    
    
    private static final AtomicInteger SUFFIX = new AtomicInteger(0);
    
    @Value(value = "${file.upload.suffix:jpg,jpeg,png,bmp,xls,xlsx,pdf,mp4}")
    private String fileUploadSuffix;
    
    @Override
    public Result<?> upload(MultipartFile file) {
        FileUploadResponse result = new FileUploadResponse();
        List<String> suffixList = Arrays.asList(fileUploadSuffix.split(","));
        
        try{
            // FileUploadResponse result = new FileUploadResponse();
            if(file.isEmpty()){
                log.error("这是一个空文件");
                return Result.fail("不能传输空文件");
            }
            //文件名
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
            log.info("上传文件名称为:{},后缀名为:{}",fileName,suffixName);
            
            //校验后缀
            if(!suffixList.contains(suffixName)){
                log.error("不能传输此文件类型");
                return Result.fail("不能传输此文件类型");
            }
            
            //如果没有文件夹创建个文件夹
            File folder = new File(filePath);
            if(!folder.exists()){
                folder.mkdir();
            }
            String localFileName = timeFormat(System.currentTimeMillis()) + SUFFIX.getAndIncrement() + "."+suffixName;
            String absolutePath = filePath+File.separator+localFileName;
            log.info("绝对路径为{}",absolutePath);
            
            //写入
            file.transferTo(new File(absolutePath));
            log.debug("写入成功");
            
            String separator = "/";
            String path = separator + pathPattern + separator + localFileName;
            result.setPath(path);
            result.setFileName(localFileName);
            
            return Result.success(result);
            
        } catch (IOException e) {
            log.error("发生错误:{}",e);
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    public static String timeFormat(Long time) {
        if (Objects.isNull(time)) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(time);
    }
}
