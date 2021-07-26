package com.tweetapp.tweetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

/**
 * @author Nithya T
 *
 */
@SpringBootApplication
@OpenAPIDefinition
@ComponentScan(basePackages = "com.tweetapp.tweetservice.*")
public class TweetserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetserviceApplication.class, args);
	}

}
