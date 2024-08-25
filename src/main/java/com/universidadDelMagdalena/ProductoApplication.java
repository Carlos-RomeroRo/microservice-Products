package com.universidadDelMagdalena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // bajo las reglas del framework SpringBoot
public class ProductoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductoApplication.class, args);
		System.out.println("\nServidor iniciado");
	}
}
