package com.pufaschool.server.utilController;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 异常页面处理
 */
@Configuration
public class WebStatusCodeHandlerController {
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");

        ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");

        ErrorPage errorPage405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405");

        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");

        factory.addErrorPages(errorPage400, errorPage403, errorPage405, errorPage500, errorPage404);

        return factory;
    }
}
