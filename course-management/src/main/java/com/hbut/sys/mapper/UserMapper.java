package com.hbut.sys.mapper;

import com.hbut.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> getRoleNameByUserId(Integer id);
}
