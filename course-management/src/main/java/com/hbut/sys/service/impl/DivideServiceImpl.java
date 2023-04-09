package com.hbut.sys.service.impl;

import com.hbut.sys.entity.Divide;
import com.hbut.sys.mapper.DivideMapper;
import com.hbut.sys.service.IDivideService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
@Service
public class DivideServiceImpl extends ServiceImpl<DivideMapper, Divide> implements IDivideService {

    @Override
    public boolean updateDivide(Divide divide) {
        return this.baseMapper.updateDivide(divide) > 0;
    }
}
