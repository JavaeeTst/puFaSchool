package com.pufaschool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//扫描dao接口创建动态代理对象
@MapperScan(basePackages = {"com.pufaschool.server.dao", "com.pufaschool.client.dao"})
//启用定时器
@EnableScheduling
//启用aop
@EnableAspectJAutoProxy
//启用事务
@EnableTransactionManagement
public class PuFaSchoolApplication {
    public static void main(String[] args) {

        System.out.print("测试");


        ConfigurableApplicationContext run = SpringApplication.run(PuFaSchoolApplication.class, args);

    }
}
