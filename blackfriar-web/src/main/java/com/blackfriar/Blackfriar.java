package com.blackfriar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.blackfriar" })
public class Blackfriar {

	public static void main(String[] args) {
        SpringApplication.run(Blackfriar.class, args);
    }
}
