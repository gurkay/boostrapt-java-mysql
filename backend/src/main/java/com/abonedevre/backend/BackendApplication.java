package com.abonedevre.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		System.out.println();
		System.out.println("\n###################################################");
		System.out.println("# class: BackendApplication ::: method: main");
		System.out.println("# Loading... ");
		System.out.println("###################################################\n");
		SpringApplication.run(BackendApplication.class, args);
	}

}
