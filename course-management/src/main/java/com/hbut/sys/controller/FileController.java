package com.hbut.sys.controller;

import ch.qos.logback.core.util.FileUtil;
import com.hbut.common.vo.Result;
import com.hbut.sys.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private FileService service;
    
    @PostMapping("/upload")
    private Result<?> upload(@RequestPart("file") MultipartFile file){
        return service.upload(file);
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
