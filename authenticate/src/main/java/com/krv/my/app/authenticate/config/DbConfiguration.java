package com.krv.my.app.authenticate.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import liquibase.configuration.GlobalConfiguration;
import liquibase.configuration.LiquibaseConfiguration;
import liquibase.integration.spring.SpringLiquibase;

/**
 * @author Khushboo_Vansh
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class DbConfiguration {

	@Autowired
	private Environment environment;

	@Value("${liquibase.context}")
	private String context;

	/**
	 * @return
	 */
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		Properties createStrategy = new Properties();
		createStrategy.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		createStrategy.setProperty("hibernate.proc.param_null_passing", "true");
		createStrategy.setProperty(AvailableSettings.POOL_SIZE, "100");
		createStrategy.setProperty(AvailableSettings.SHOW_SQL, "true");
		emf.setJpaProperties(createStrategy);
		emf.setPackagesToScan("com.krv.my.app.authenticate.entity");
		emf.setJpaVendorAdapter(getJpaVendorAdapter());
		emf.setDataSource(getDataSource());
		return emf;
	}

	/**
	 * @return
	 */
	@Bean(name = "adminDataSource")
	public DataSource getAdminDataSource() {
		if (!StringUtils.isEmpty(environment.getProperty("jdbc.jndi.admin"))) {
			JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
			return dataSourceLookup.getDataSource(environment.getProperty("jdbc.jndi.admin"));
		} else {
			return DataSourceBuilder.create().username(environment.getProperty("myapp.db.username"))
					.password(environment.getProperty("myapp.db.password")).url(environment.getProperty("myapp.db.url"))
					.build();
		}
	}

	/**
	 * @return
	 */
	@Bean(name = "dataSource")
	@Primary
	public DataSource getDataSource() {
		if (!StringUtils.isEmpty(environment.getProperty("jdbc.jndi"))) {
			JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
			return dataSourceLookup.getDataSource(environment.getProperty("jdbc.jndi"));
		} else {
			return DataSourceBuilder.create().username(environment.getProperty("myapp.db.username"))
					.password(environment.getProperty("myapp.db.password")).url(environment.getProperty("myapp.db.url"))
					.build();
		}
	}

	/**
	 * @return
	 */
	@Bean("jpaVendorAdapter")
	public JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(false);
		jpaVendorAdapter.setDatabasePlatform(environment.getProperty("hibernate.dialect"));
		return jpaVendorAdapter;
	}

	/**
	 * @return
	 */
	@Bean("liquibase")
	public SpringLiquibase liquibase() {
		GlobalConfiguration liquibaseConfiguration = LiquibaseConfiguration.getInstance()
				.getConfiguration(GlobalConfiguration.class);
		liquibaseConfiguration.setDatabaseChangeLogTableName("myapp_liquibase_dbchangelog");
		liquibaseConfiguration.setDatabaseChangeLogLockTableName("myapp_liquibase_dbchangeloglock");
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(getAdminDataSource());
		liquibase.setChangeLog("classpath:db/liquibase/changelog-master.xml");
		liquibase.setShouldRun(true);
		liquibase.setContexts(context);
		liquibase.setDefaultSchema("myapp");
		return liquibase;
	}

	/**
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
