package com.hbut.sys.mapper;

import com.hbut.sys.entity.tempList.SelectC;
import com.hbut.sys.entity.Stu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

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

    public List<SelectC> selectCourse(String sno, String sname, String sclass,Integer pageFr,Integer pageSize);

    long getSCListCount(String sno, String sname,String sclass);
}
