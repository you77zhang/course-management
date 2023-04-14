package com.hbut;

import com.hbut.sys.entity.Stu;
import com.hbut.sys.entity.tempList.SelectC;
import com.hbut.sys.entity.User;
import com.hbut.sys.mapper.StuMapper;
import com.hbut.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CourseManagementApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private StuMapper stuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

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

    @Test
    void testRedis(){
        // redisTemplate.opsForValue().set("name","zyf");
        // System.out.println(redisTemplate.opsForValue().get("name"));
        Stu stu = new Stu();
        stu.setSno("2010300104");
        stu.setSname("张宇帆");
        redisTemplate.opsForValue().set("stu",stu);
        System.out.println(redisTemplate.opsForValue().get("stu"));
    }
}
