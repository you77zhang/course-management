package com.hbut.sys.mapper;

import com.hbut.sys.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hbut.sys.entity.tempList.TeachC;
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
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Update("update teacher set tname = #{tname},tsex = #{tsex},tdept = #{tdept} where tno = #{tno};")
    int updateTea(Teacher teacher);

    List<TeachC> getTcList(String tno,String tname,Long pageFr,Long pageSize);
    Long getTcListCount(String tno,String tname);

}
