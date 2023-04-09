package com.hbut.sys.service.impl;

import com.hbut.sys.entity.Teacher;
import com.hbut.sys.entity.tempList.TeachC;
import com.hbut.sys.mapper.TeacherMapper;
import com.hbut.sys.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Override
    public boolean updateTeacher(Teacher teacher) {
        return baseMapper.updateTea(teacher) > 0;
    }

    @Override
    public List<TeachC> getTcList(String tno, String tname, Long pageNo, Long pageSize) {
        if(tno!=null && tno == "") tno = null;
        if(tname!=null && tname == "") tname = null;
        Long pageFr = (pageNo-1)*pageSize;
        return baseMapper.getTcList(tno,tname,pageFr,pageSize);
    }

    @Override
    public Long getTcListCount(String tno, String tname) {
        return baseMapper.getTcListCount(tno,tname);
    }
}
