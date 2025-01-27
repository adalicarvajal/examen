package com.espe.micro_taller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroTallerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroTallerApplication.class, args);
	}
}
