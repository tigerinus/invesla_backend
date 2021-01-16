package com.tensorsmart.invesla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InveslaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InveslaApplication.class, args);
	}

}
