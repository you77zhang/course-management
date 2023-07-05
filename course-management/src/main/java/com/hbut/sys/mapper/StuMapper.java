package com.hbut.sys.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbut.sys.entity.tempList.SelectC;
import com.hbut.sys.entity.Stu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface StuMapper extends BaseMapper<Stu> {
    @Update("update stu set sname=#{sname},ssex=#{ssex},sdept=#{sdept}," +
            "sclass=#{sclass},sassign=#{sassign}  where sno=#{sno};")
    public int updateStu(Stu stu);

    public Page<SelectC> selectCourse(@Param("page") Page<SelectC> page,String sno, String sname, String sclass);
}
