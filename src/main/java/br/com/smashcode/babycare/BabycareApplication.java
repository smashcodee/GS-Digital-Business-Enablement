package br.com.smashcode.babycare;

import br.com.smashcode.babycare.utils.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BabycareApplication {

	public static void main(String[] args) {
		EnvLoader.loadEnv();
		SpringApplication.run(BabycareApplication.class, args);
	}

}
