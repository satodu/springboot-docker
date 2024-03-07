package br.com.screenwatcher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ScreenwatcherApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenwatcherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Obtém a data e hora atuais
		LocalDateTime now = LocalDateTime.now();

		// Formata a data e hora
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedNow = now.format(formatter);

		// Imprime a data e hora formatadas
		System.out.println("Starting API PANDA :: " + formattedNow);
	}
}
