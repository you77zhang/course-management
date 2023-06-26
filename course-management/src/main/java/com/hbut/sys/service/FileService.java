package com.hbut.sys.service;

import com.hbut.common.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : zhangyf
 * @project: com.hbut.sys.service
 * @date: 2023/6/26 11:39
 */
public interface FileService {
    public Result<?> upload(MultipartFile file);
}
