package com.hbut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hbut.*.mapper")
public class CourseManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseManagementApplication.class, args);
    }

}
