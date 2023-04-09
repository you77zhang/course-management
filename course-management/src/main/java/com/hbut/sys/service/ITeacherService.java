package com.hbut.sys.service;

import com.hbut.sys.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hbut.sys.entity.tempList.TeachC;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface ITeacherService extends IService<Teacher> {

    boolean updateTeacher(Teacher teacher);

    List<TeachC> getTcList(String tno, String tname, Long pageNo, Long pageSize);

    Long getTcListCount(String tno, String tname);
}
