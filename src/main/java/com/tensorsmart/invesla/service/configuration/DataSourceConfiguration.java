package com.tensorsmart.invesla.service.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

// This class is needed to allow secondary data source(s) defined somewhere else
// without hijacking the default data source.
@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource primaryDataSource() {
        DataSource dataSource = primaryDataSourceProperties().initializeDataSourceBuilder().build();
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean entityManager = builder.dataSource(primaryDataSource())
                .packages(new String[] { "com.tensorsmart.invesla.repository.entity",
                        "com.tensorsmart.invesla.questrade.repository.entity" })
                .build();

        return entityManager;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(builder).getObject());

        return transactionManager;
    }
}
