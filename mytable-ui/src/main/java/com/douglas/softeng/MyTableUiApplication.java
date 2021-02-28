package com.douglas.softeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.douglas.softeng"})
@EntityScan(basePackages="com.douglas.softeng.model")
public class MyTableUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTableUiApplication.class, args);
	}

}
