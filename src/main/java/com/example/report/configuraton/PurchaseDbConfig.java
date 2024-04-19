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
@EnableJpaRepositories(entityManagerFactoryRef = "purchaseEntityManagerFactory", basePackages = {
        "com.example.report.purchase" })
public class PurchaseDbConfig {

    @Bean("purchaseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.purchase")
    public DataSource purchaseDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "purchaseEntityManagerFactory")
    @ConfigurationProperties(prefix = "spring.datasource.purchase")
    public LocalContainerEntityManagerFactoryBean purchaseEntityManagerFactory(@Qualifier("purchaseDataSource")DataSource purchaseDataSource, EntityManagerFactoryBuilder builder) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return builder.dataSource(purchaseDataSource)
                .packages("com.example.report.purchase")
                .persistenceUnit("ProductAndPurchaseAndPurchaseProduct")
                .properties(properties).build();
        /**
         * Alternate Way of implementing multiple data sources
//        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
//        emfb.setDataSource(purchaseDataSource);
//        emfb.setPackagesToScan("com.example.report");
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        emfb.setJpaVendorAdapter(vendorAdapter);
//        return emfb;
         */
    }
}
