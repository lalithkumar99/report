package com.example.report.configuraton;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
        "com.example.report.customer" })
public class CustomerDbConfig {

    @Primary
    @Bean("customerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.customer")
    public DataSource customerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    @ConfigurationProperties(prefix = "spring.datasource.customer")
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(EntityManagerFactoryBuilder builder,@Qualifier("customerDataSource")DataSource customerDataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return builder.dataSource(customerDataSource).properties(properties)
                .packages("com.example.report.customer")
                .persistenceUnit("AddressAndCustomer")
                .build();

        /**
         * Alternate Way of implementing multiple data sources
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(customerDataSource);
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



