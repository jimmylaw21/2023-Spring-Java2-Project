package cse.java2.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This is the main class of the Spring Boot application.
 */
@SpringBootApplication
//@ComponentScan("cse.java2.project.controller") // 设置扫描的包路径
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
