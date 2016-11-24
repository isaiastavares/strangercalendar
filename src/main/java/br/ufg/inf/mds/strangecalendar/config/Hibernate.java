package br.ufg.inf.mds.strangecalendar.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Classe responsável pela configuração do
 * Banco de Dados e Hibernate.
 *
 * @author Isaias Tavares
 */
@Configuration
public class Hibernate {

	@Value("${strange.calendar.dataSource.driverClassName}")
	private String driver;
	@Value("${strange.calendar.dataSource.url}")
	private String url;
	@Value("${strange.calendar.dataSource.username}")
	private String username;
	@Value("${strange.calendar.dataSource.password}")
	private String password;

	@Value("${strange.calendar.hibernate.dialect}")
	private String dialect;
	@Value("${strange.calendar.hibernate.hbm2ddl.auto}")
	private String hbm2ddlAuto;
	@Value("${strange.calendar.hibernate.show_sql}")
	private String showSql;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource =
				new DriverManagerDataSource();

		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}

	@Bean
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean emf =
				new LocalContainerEntityManagerFactoryBean();

		Map<String, String> properties = new HashMap<>();

		properties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
		properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
		properties.put(org.hibernate.cfg.Environment.SHOW_SQL, showSql);

		// Pacote base para procurar classes anotadas com @Entity
		emf.setPackagesToScan("br.ufg.inf.mds.strangecalendar");
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaPropertyMap(properties);
		return emf;
	}

	/**
	 * Cria um gerenciador de transações. Executa as transações de forma
	 * automática
	 *
	 * @param managerFactory
	 * @return
	 */
	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(
			EntityManagerFactory managerFactory) {

		return new JpaTransactionManager(managerFactory);
	}
}