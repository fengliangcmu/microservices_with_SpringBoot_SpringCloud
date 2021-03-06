package com.example.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 
 * @author Feng
 * 
 * With multiple properties defined, we access them by following links:
 * http://localhost:8888/limits-service/qa
 * http://localhost:8888/limits-service/dev
 * http://localhost:8888/limits-service/default
 *
 */

@EnableConfigServer //has to be added otherwise 404
@SpringBootApplication
public class SpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
	}

}

