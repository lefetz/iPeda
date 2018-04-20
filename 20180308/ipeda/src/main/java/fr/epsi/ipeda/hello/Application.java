package fr.epsi.ipeda.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "fr.epsi.ipeda")
@ComponentScan(basePackages = "fr.epsi.ipeda")
@EntityScan("fr.epsi.ipeda.dal.entity")
@EnableJpaRepositories(basePackages = "fr.epsi.ipeda.dal.repository")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}