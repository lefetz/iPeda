package fr.epsi.ipeda.configuration;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import fr.epsi.ipeda.dal.entity.DatabaseProperty;
import fr.epsi.ipeda.dal.repository.DatabasePropertyRepository;

@Component
public class PropertyEnvironment implements ApplicationListener<ContextRefreshedEvent> {
	private final ConfigurableEnvironment configurableEnvironment;
	private final DatabasePropertyRepository propertiesRepository;

	@Autowired
	private Environment env;

	@Autowired
	private DatabasePropertyRepository databasePropertyRepository;

	@Autowired
	public PropertyEnvironment(ConfigurableEnvironment configurableEnvironment, DatabasePropertyRepository propertiesRepository) {
		this.configurableEnvironment = configurableEnvironment;
		this.propertiesRepository = propertiesRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (env.getProperty("hibernate.hbm2ddl.auto").startsWith("create")) {
			databasePropertyRepository.save(new DatabaseProperty("timeSeparator", "h"));
			databasePropertyRepository.save(new DatabaseProperty("heureAMDebut", "08h15"));
			databasePropertyRepository.save(new DatabaseProperty("heureAMFin", "12h15"));
			databasePropertyRepository.save(new DatabaseProperty("heurePMDebut", "13h45"));
			databasePropertyRepository.save(new DatabaseProperty("heurePMFin", "17h45"));
		}

		final Map<String, Object> properties = propertiesRepository.findAll().stream().collect(Collectors.toMap(DatabaseProperty::getName, DatabaseProperty::getValue));
		configurableEnvironment.getPropertySources().addLast(new MapPropertySource("db", properties));

	}
}