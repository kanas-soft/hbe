package com.kanas.hbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.kanas.hbe"})
public class HbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbeApplication.class, args);
	}

}
