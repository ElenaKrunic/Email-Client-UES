package com.uns.ac.rs.ues.Email.Client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan
public class EmailClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailClientApplication.class, args);
	}

	protected void configure(HttpSecurity security) throws Exception
    {
     security.httpBasic().disable();
    }
}
