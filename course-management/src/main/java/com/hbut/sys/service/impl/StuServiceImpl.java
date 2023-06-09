package com.hbut.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbut.sys.entity.tempList.SelectC;
import com.hbut.sys.entity.Stu;
import com.hbut.sys.mapper.StuMapper;
import com.hbut.sys.service.IStuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Stu> implements IStuService {

    @Override
    public boolean updateStu(Stu stu) {
        return baseMapper.updateStu(stu) > 0;
    }

    @Override
    public Page<SelectC> selectCourse(Page<SelectC> page,String sno, String sname, String sclass) {
        if(sno=="") sno = null;
        if(sname == "") sname = null;
        if(sclass == "") sclass = null;
        return baseMapper.selectCourse(page,sno,sname,sclass);
    }



    // @Override
    // public List<SelectC> selectCourse(String sno, String sname, String sclass, Integer pageNo, Integer pageSize) {
    //     return baseMapper.selectCourse(sno,sname,sclass,pageNo);
    // }
}
