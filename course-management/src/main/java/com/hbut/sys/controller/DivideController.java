package com.hbut.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbut.common.vo.Result;
import com.hbut.sys.entity.Divide;
import com.hbut.sys.service.IDivideService;
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
@RequestMapping("/divide")
public class DivideController {
    @Autowired
    private IDivideService divideService;

    @GetMapping("/list")
    public Result<Map<String,Object>> getDivide(@RequestParam(value = "gno",required = false) Integer gno,
                                                @RequestParam(value = "sgroup",required = false) String sgroup,
                                                @RequestParam(value = "pageNo") Long pageNo,
                                                @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<Divide> wrapper = new LambdaQueryWrapper<>();
        if(gno!=null) wrapper.eq(Divide::getGno,gno);
        wrapper.eq(StringUtils.hasLength(sgroup),Divide::getSgroup,sgroup);
        wrapper.orderByAsc(Divide::getGno);

        Page<Divide> page = new Page<>(pageNo, pageSize);
        divideService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    @PostMapping
    public Result<?> addDivide(@RequestBody Divide divide){
        LambdaQueryWrapper<Divide> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Divide::getGno,divide.getGno());

        Divide getone = divideService.getOne(wrapper);
        if(getone == null){
            divideService.save(divide);
            return Result.success("新增用户成功");
        }
        else return Result.fail("用户已存在");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteDivide(@PathVariable Integer id){
        LambdaQueryWrapper<Divide> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Divide::getGno,id);
        divideService.remove(wrapper);
        return Result.success("删除成功");
    }

    @PutMapping()
    public Result<?> updateSty(@RequestBody Divide divide){
        divideService.updateDivide(divide);
        return Result.success("修改成功");
    }

    @GetMapping("/{gno}")
    public Result<?> getDivideByGno(@PathVariable Integer gno){
        System.out.println(gno);
        LambdaQueryWrapper<Divide> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Divide::getGno,gno);

        Divide getone = divideService.getOne(wrapper);
        return Result.success(getone);
    }
}
