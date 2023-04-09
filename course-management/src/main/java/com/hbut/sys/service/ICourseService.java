package com.hbut.sys.service;

import com.hbut.sys.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface ICourseService extends IService<Course> {

    boolean updateCourse(Course course);
}
