package br.com.equals.xtuff.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"br.com.equals.xtuff"})
@EntityScan(basePackages="br.com.equals.xtuff.domain.entities")
public class AppMain{

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}