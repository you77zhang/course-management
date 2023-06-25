package com.hbut.sys.controller;

import ch.qos.logback.core.util.FileUtil;
import com.hbut.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author : zhangyf
 * @project: com.hbut.sys.controller
 * @date: 2023/6/24 16:44
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {
    
    @Value("${file.upload.dir}")
    private String uploadFilePath;
    
    @PostMapping("/upload")
    private Result<?> upload(@RequestPart("file") MultipartFile file){
        if(file.isEmpty()){
            return Result.fail("空文件");
        }
        //文件名
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传文件名称为:{},后缀名为:{}",fileName,suffixName);
        
        File fileTempObj = new File(uploadFilePath + File.separator + fileName);
        //检测目录是否存在
        if(!fileTempObj.getParentFile().exists()){
            fileTempObj.getParentFile().mkdirs();
        }
        //使用文件名检测文件是否已经存在
        if(fileTempObj.exists()){
            return Result.fail("文件已存在！");
        }
        
        try{
            //写入
            file.transferTo(fileTempObj);
        } catch (IOException e) {
            log.error("发生错误:{}",e);
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
        return Result.success("上传文件成功");
    }
    
    // @GetMapping("/download")
    // public Result<?> download(@RequestParam ){
    //    
    // }
    
    @GetMapping("/test")
    public Result<?> test(){
        return Result.success(uploadFilePath);
    }
    
}
