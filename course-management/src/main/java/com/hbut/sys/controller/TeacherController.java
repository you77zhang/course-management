package com.hbut.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbut.common.vo.Result;
import com.hbut.sys.entity.Teacher;
import com.hbut.sys.entity.tempList.TeachC;
import com.hbut.sys.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getTeacher(@RequestParam(value = "tno",required = false) String tno,
                                             @RequestParam(value = "tname",required = false) String tname,
                                             @RequestParam(value = "pageNo") Long pageNo,
                                             @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(tno),Teacher::getTno,tno);
        wrapper.eq(StringUtils.hasLength(tname),Teacher::getTname,tname);
        wrapper.orderByAsc(Teacher::getTno);

        Page<Teacher> page = new Page<>(pageNo, pageSize);
        teacherService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @GetMapping("/tcList")
    public Result<Map<String,Object>> getTcList(@RequestParam(value = "tno",required = false) String tno,
                                                 @RequestParam(value = "tname",required = false) String tname,
                                                 @RequestParam(value = "pageNo") Long pageNo,
                                                 @RequestParam(value = "pageSize") Long pageSize){

        List<TeachC> tcList = teacherService.getTcList(tno, tname, pageNo, pageSize);
        Long total = teacherService.getTcListCount(tno, tname);

        Map<String,Object> data = new HashMap<>();
        data.put("total",total);
        data.put("rows",tcList);

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addTeacher(@RequestBody Teacher teacher){
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(teacher.getTno()),Teacher::getTno,teacher.getTno());

        Teacher getone = teacherService.getOne(wrapper);
        if(getone == null){
            teacherService.save(teacher);
            return Result.success("新增用户成功");
        }
        else return Result.fail("用户已存在");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteTeacher(@PathVariable String id){
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTno,id);
        teacherService.remove(wrapper);
        return Result.success("删除成功");
    }

    @PutMapping()
    public Result<?> updateSty(@RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher);
        return Result.success("修改成功");
    }

    @GetMapping("/{tno}")
    public Result<?> getTeacherByTno(@PathVariable String tno){
        System.out.println(tno);
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Teacher::getTno,tno);

        Teacher getone = teacherService.getOne(wrapper);
        return Result.success(getone);
    }
}
