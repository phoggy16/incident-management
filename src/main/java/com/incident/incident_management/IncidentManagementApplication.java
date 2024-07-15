package com.incident.incident_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class IncidentManagementApplication {

	@Bean
	public BCryptPasswordEncoder passwordEncoderBean()
	{
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {
		SpringApplication.run(IncidentManagementApplication.class, args);
	}

}
