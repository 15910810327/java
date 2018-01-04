package com.example.lyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
@ComponentScan
public class SpringbootAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAopApplication.class, args);
	}
}
