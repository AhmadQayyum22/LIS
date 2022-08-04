package com.perficient.Lab.Information.System;

import com.perficient.Lab.Information.System.Security.PasswordAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class LabInformationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabInformationSystemApplication.class, args);
	}



}
