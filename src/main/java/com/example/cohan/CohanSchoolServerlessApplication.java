package com.example.cohan;

import com.example.cohan.controller.PingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ PingController.class })
public class CohanSchoolServerlessApplication {

	public static void main(String[] args) {
		SpringApplication.run(CohanSchoolServerlessApplication.class, args);
	}

}
