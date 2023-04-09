package com.hbut.sys.service;

import com.hbut.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);
}
