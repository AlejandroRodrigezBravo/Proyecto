package com.arodriguezbravo.catalago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ArodriguezbravoCatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArodriguezbravoCatalogoApplication.class, args);
	}

}