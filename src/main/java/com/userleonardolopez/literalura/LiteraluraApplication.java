package com.userleonardolopez.literalura;

import com.userleonardolopez.literalura.principal.Principal;
import com.userleonardolopez.literalura.repository.AutorRepository;
import com.userleonardolopez.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var principal = new Principal(libroRepository, autorRepository);
		principal.mostrarMenu();
	}
}
