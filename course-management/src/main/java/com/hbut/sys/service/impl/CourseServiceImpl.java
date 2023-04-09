package com.hbut.sys.service.impl;

import com.hbut.sys.entity.Course;
import com.hbut.sys.mapper.CourseMapper;
import com.hbut.sys.service.ICourseService;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Override
    public boolean updateCourse(Course course) {
        return baseMapper.updateC(course) > 0;
    }
}
