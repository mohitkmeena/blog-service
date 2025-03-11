package com.mohit.blogwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BlogWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogWebsiteApplication.class, args);
	}

}
