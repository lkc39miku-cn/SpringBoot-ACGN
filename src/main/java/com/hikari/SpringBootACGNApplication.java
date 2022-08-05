package com.hikari;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * SpringBootACGNApplication
 *
 * @author lkc39miku_cn
 */
@ServletComponentScan
@MapperScan("com.hikari.project.**.mapper")
@SpringBootApplication
public class SpringBootACGNApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootACGNApplication.class, args);
    }
}
