package com.tensorsmart.invesla.questrade.service.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration()
@EnableJpaRepositories(
    basePackages = {"com.tensorsmart.invesla.questrade.repository"},
    entityManagerFactoryRef = "tokenEntityManagerFactory",
    transactionManagerRef = "tokenTransactionManager"
)
public class TokenDataSourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.token-datasource")
    public DataSourceProperties tokenDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.token-datasource.configuration")
    public DataSource tokenDataSource() {
        DataSource dataSource = tokenDataSourceProperties().initializeDataSourceBuilder().build();
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean tokenEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean entityManager = builder.dataSource(tokenDataSource())
                .packages(new String[] { "com.tensorsmart.invesla.questrade.repository.entity" }).build();

        return entityManager;
    }

    @Bean
    public PlatformTransactionManager tokenTransactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(tokenEntityManagerFactory(builder).getObject());

        return transactionManager;
    }
}
