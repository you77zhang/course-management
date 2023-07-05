package com.hbut.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbut.common.aop.LogAnnotation;
import com.hbut.common.vo.Result;
import com.hbut.sys.entity.tempList.SelectC;
import com.hbut.sys.entity.Stu;
import com.hbut.sys.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/stu")
public class StuController {
    @Autowired
    private IStuService stuService;

    @GetMapping("/list")
    @LogAnnotation(module = "学生",operator = "获取学生列表")
    public Result<Map<String,Object>> getStu(@RequestParam(value = "sno",required = false) String sno,
                                             @RequestParam(value = "sname",required = false) String sname,
                                             @RequestParam(value = "pageNo") Long pageNo,
                                             @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Stu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(sno),Stu::getSno,sno);
        wrapper.eq(StringUtils.hasLength(sname),Stu::getSname,sname);
        wrapper.orderByAsc(Stu::getSno);

        Page<Stu> page = new Page<>(pageNo, pageSize);
        stuService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }
    @GetMapping("/sclist")
    public Result<Map<String,Object>> getScList(@RequestParam(value = "sno",required = false) String sno,
                                             @RequestParam(value = "sname",required = false) String sname,
                                             @RequestParam(value = "sclass",required = false) String sclass,
                                             @RequestParam(value = "pageNo") Integer pageNo,
                                             @RequestParam(value = "pageSize") Integer pageSize){
        
        Page<SelectC> page = new Page<SelectC>(pageNo, pageSize);
        stuService.selectCourse(page,sno, sname, sclass);
        // long total = stuService.getSCListCount(sno, sname,sclass);
        // // long total = stuService.count();
        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }
    @PostMapping
    @LogAnnotation(module = "学生",operator = "增加学生")
    public Result<?> addStu(@RequestBody Stu stu){
        LambdaQueryWrapper<Stu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(stu.getSno()),Stu::getSno,stu.getSno());

        Stu getone = stuService.getOne(wrapper);
        if(getone == null){
            stuService.save(stu);
            return Result.success("新增用户成功");
        }
        else return Result.fail("用户已存在");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteStu(@PathVariable String id){
        LambdaQueryWrapper<Stu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Stu::getSno,id);
        stuService.remove(wrapper);
        return Result.success("删除成功");
    }

    @PutMapping()
    public Result<?> updateSty(@RequestBody Stu stu){
        stuService.updateStu(stu);
        return Result.success("修改成功");
    }

    @GetMapping("/{sno}")
    public Result<?> getStuBySno(@PathVariable String sno){
        System.out.println(sno);
        LambdaQueryWrapper<Stu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Stu::getSno,sno);

        Stu getone = stuService.getOne(wrapper);
        return Result.success(getone);
    }
}
