package com.ace.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ace.training")
public class StudentReportAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentReportAppApplication.class, args);
	}
}
