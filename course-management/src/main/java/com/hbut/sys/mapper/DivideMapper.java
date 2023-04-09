package com.hbut.sys.mapper;

import com.hbut.sys.entity.Divide;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface DivideMapper extends BaseMapper<Divide> {

    int updateDivide(Divide divide);
}
