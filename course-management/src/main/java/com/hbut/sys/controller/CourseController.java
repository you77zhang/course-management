package com.hbut.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbut.common.vo.Result;
import com.hbut.sys.entity.Course;
import com.hbut.sys.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getCourse(@RequestParam(value = "cno",required = false) String cno,
                                                 @RequestParam(value = "cname",required = false) String cname,
                                                 @RequestParam(value = "pageNo") Long pageNo,
                                                 @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(cno),Course::getCno,cno);
        wrapper.eq(StringUtils.hasLength(cname),Course::getCname,cname);
        wrapper.orderByAsc(Course::getCno);

        Page<Course> page = new Page<>(pageNo, pageSize);
        courseService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addCourse(@RequestBody Course course){
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(course.getCno()),Course::getCno,course.getCno());

        Course getone = courseService.getOne(wrapper);
        if(getone == null){
            courseService.save(course);
            return Result.success("新增用户成功");
        }
        else return Result.fail("用户已存在");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteCourse(@PathVariable String id){
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getCno,id);
        courseService.remove(wrapper);
        return Result.success("删除成功");
    }

    @PutMapping()
    public Result<?> updateSty(@RequestBody Course course){
        courseService.updateCourse(course);
        return Result.success("修改成功");
    }

    @GetMapping("/{cno}")
    public Result<?> getCourseByCno(@PathVariable String cno){
        System.out.println(cno);
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getCno,cno);

        Course getone = courseService.getOne(wrapper);
        return Result.success(getone);
    }
}
