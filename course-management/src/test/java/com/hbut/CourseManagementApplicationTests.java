package com.hbut;

import com.hbut.sys.entity.tempList.SelectC;
import com.hbut.sys.entity.User;
import com.hbut.sys.mapper.StuMapper;
import com.hbut.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CourseManagementApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private StuMapper stuMapper;

    @Test
    void test() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void test2(){
        List<SelectC> a = stuMapper.selectCourse(null, "张宇帆", null,0,5);
        System.out.println(a);
    }

}
