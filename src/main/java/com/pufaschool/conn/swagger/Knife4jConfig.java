package com.pufaschool.conn.swagger;

import com.pufaschool.conn.result.Status;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    @Bean
    public Docket adminApiConfig() {

        List<Parameter> pars = new ArrayList<>();

        ParameterBuilder tokenPar = new ParameterBuilder();

        tokenPar.name("")
                .description("每次请求都得携带token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        pars.add(tokenPar.build());

        Docket adminApi = new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pufaschool"))
                .paths(PathSelectors.regex("/system/puFaSchool/.*"))
                .build()
                .globalOperationParameters(pars);

        return adminApi;
    }

    @Bean
    public ApiInfo adminApiInfo() {

        return new ApiInfoBuilder()
                .title("后台管理系统Api")
                .description("本文档详细的介绍每个接口的功能，登录之后会返回一个token，每次访问请求请务必把那个token放入到请求头里面来")
                .version("1.0")
                .contact(new Contact("dmr", "http://dmr.com", "dmr@qq.com"))
                .termsOfServiceUrl("后台管理文档")
                .build();
    }
}