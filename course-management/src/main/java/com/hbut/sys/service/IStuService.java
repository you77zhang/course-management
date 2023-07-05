package com.hbut.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbut.sys.entity.tempList.SelectC;
import com.hbut.sys.entity.Stu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface IStuService extends IService<Stu> {

    boolean updateStu(Stu stu);
    public Page<SelectC> selectCourse(Page<SelectC> page,String sno, String sname, String sclass);
}
