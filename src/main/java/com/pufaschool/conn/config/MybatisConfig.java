package com.pufaschool.conn.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus分页查询
 */
@Configuration
public class MybatisConfig {


    /**
     * mybatis分页插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return interceptor;
    }
    /**
     * mybatis-plus防止传给前端id精度丢失
     */
    @Bean
    public IKeyGenerator keyGenerator(){

        return new H2KeyGenerator();
    }
}
