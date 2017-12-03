package fr.epsi.ipeda.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "fr.epsi.ipeda")
@EntityScan(basePackages = "fr.epsi.ipeda.dao.entity")
@EnableJpaRepositories(basePackages = "fr.epsi.ipeda.dao.repository")
public class ApplicationConfig {

}
