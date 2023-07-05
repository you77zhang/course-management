package com.hbut;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hbut.sys.entity.Stu;
import com.hbut.utils.EmailUtil;
import com.hbut.sys.entity.tempList.SelectC;
import com.hbut.sys.entity.User;
import com.hbut.sys.mapper.StuMapper;
import com.hbut.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@SpringBootTest
class CourseManagementApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private StuMapper stuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EmailUtil emailUtil;
    
    @Test
    void test() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void test2(){
        Page<SelectC> page = new Page<SelectC>(1,10);
        stuMapper.selectCourse(page,null, "张宇帆", null);
        System.out.println(page);
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

    @Test
    void sendStringEmail() {
        // 测试文本邮件发送（无附件）
        String to = "1476203673@qq.com"; // 这是个假邮箱，写成你自己的邮箱地址就可以
        String title = "文本邮件发送测试";
        String content = "文本邮件发送测试";
        emailUtil.sendMessage(to, title, content);
    }

    @Test
    void sendFileEmail() {
        // 测试单个附件邮件发送
        String to = "754849136@qq.com"; // 这是个假邮箱，写成你自己的邮箱地址就可以
        String title = "单个附件邮件发送测试";
        String content = "单个附件邮件发送测试";
        File file = new File("C:\\Users\\14762\\Pictures\\Camera Roll\\4.png");
        emailUtil.sendMessageCarryFile(to, title, content, file);
    }

    @Test
    void sendFilesEmail() {
        // 测试多个附件邮件发送
        String to = "135472099@qq.com"; // 这是个假邮箱，写成你自己的邮箱地址就可以
        String title = "多个附件邮件发送测试";
        String content = "多个附件邮件发送测试";
        File[] files = new File[2];
        files[0] = new File("C:\\Users\\root\\Desktop\\配置邮箱\\1.png");
        files[1] = new File("C:\\Users\\root\\Desktop\\配置邮箱\\2.png");
        emailUtil.sendMessageCarryFiles(to, title, content, files);
    }
}
