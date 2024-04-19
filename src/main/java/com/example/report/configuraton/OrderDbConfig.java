package com.example.report.configuraton;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "orderEntityManagerFactory", basePackages = {
        "com.example.report.order" })
public class OrderDbConfig {

    @Bean("orderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.orderdb")
    public DataSource orderDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "orderEntityManagerFactory")
    @ConfigurationProperties(prefix = "spring.datasource.orderdb")
    public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(@Qualifier("orderDataSource")DataSource orderDataSource, EntityManagerFactoryBuilder builder) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return builder.dataSource(orderDataSource)
                .packages("com.example.report.order")
                .persistenceUnit("OrderAndOrderLineItem")
                .properties(properties).build();
        /**
         * Alternate Way of implementing multiple data sources
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(orderDataSource);
        emfb.setPackagesToScan("com.example.report");
        Properties properties= new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        emfb.setJpaProperties(properties);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emfb.setJpaVendorAdapter(vendorAdapter);
        return emfb;
         */

    }
}



