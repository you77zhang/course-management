package com.hbut.sys.service;

import com.hbut.sys.entity.Divide;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public interface IDivideService extends IService<Divide> {
    @Update("update divide set d_cno = #{dCno},d_sno = #{dSno},gscore = #{gscore},sclass = #{sclass},sgroup=#{sgroup}  where gno = #{gno};")
    boolean updateDivide(Divide divide);
}
