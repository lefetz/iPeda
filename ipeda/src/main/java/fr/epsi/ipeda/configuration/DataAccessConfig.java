package fr.epsi.ipeda.configuration;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "fr.epsi.ipeda" })
@EntityScan("fr.epsi.ipeda.dal.entity")
@EnableJpaRepositories(basePackages = "fr.epsi.ipeda.dal.repository")
@PropertySource({ "classpath:database.properties" })
public class DataAccessConfig {

	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(false);
		vendorAdapter.setShowSql(Boolean.TRUE);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		// introspection pour assigner la valeur de setPackagesToScan() en fonction de
		// la valeur de @ComponentScan()
		String[] packagesToScan = null;
		for (Annotation annotation : DataAccessConfig.class.getAnnotations()) {
			Class<? extends Annotation> type = annotation.annotationType();
			if (type.getName().equals("org.springframework.context.annotation.ComponentScan")) {
				for (Method method : type.getDeclaredMethods()) {
					if (method.getName().equals("basePackages")) {
						try {
							packagesToScan = (String[]) method.invoke(annotation, (Object[]) null);
						} catch (IllegalAccessException e) {
							logger.error(
									"Erreur lors de l'invocation de la méthode dynamique '" + method.getName() + "'");
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							logger.error(
									"Erreur lors de l'invocation de la méthode dynamique '" + method.getName() + "'");
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							logger.error(
									"Erreur lors de l'invocation de la méthode dynamique '" + method.getName() + "'");
							e.printStackTrace();
						}
					}
				}
			}
		}
		Assert.notNull(packagesToScan, "'packagesToScan' should not be null");
		factory.setPackagesToScan(packagesToScan);

		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(dataSource());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		jpaProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		jpaProperties.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
		jpaProperties.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation"));
		jpaProperties.put("hibernate.connection.autoReconnect", env.getProperty("hibernate.connection.autoReconnect"));
		jpaProperties.put("hibernate.connection.autoReconnectForPools",
				env.getProperty("hibernate.connection.autoReconnectForPools"));
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		factory.setJpaProperties(jpaProperties);
		return factory;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactoryBean().getObject());
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
