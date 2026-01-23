package com.example.ORTAduit;

import com.example.ORTAduit.entities.Report;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class OrtAduitApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OrtAduitApplication.class, args);

		Report report = new Report();
		File file = new File("C:\\Users\\nowakor\\Downloads\\Mortagage\\The latest\\saver\\od 0710\\ORTAduit\\sites\\brsd1.txt");
		Scanner scanner = new Scanner(file);

		while(scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
	}

}
