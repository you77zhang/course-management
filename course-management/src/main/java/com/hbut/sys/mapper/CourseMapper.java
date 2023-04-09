package com.hbut.sys.mapper;

import com.hbut.sys.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface CourseMapper extends BaseMapper<Course> {
    @Update("update course set cname = #{cname},c_tno = #{cTno},credit = #{credit},cterm = #{cterm} where cno=#{cno};")
    int updateC(Course course);
}
