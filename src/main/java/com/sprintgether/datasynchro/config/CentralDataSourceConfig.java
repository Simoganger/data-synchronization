package com.sprintgether.datasynchro.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@EnableTransactionManagement
public class CentralDataSourceConfig {

  @Bean(name = "centralDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.central")
  public DataSource centralDatasource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "centralEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean centralEntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("centralDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource)
        .packages("com.sprintgether.datasynchro.model.central")
        .persistenceUnit("central")
        .build();
  }

  @Bean(name = "centralTransactionManager")
  public PlatformTransactionManager centralTransactionManager(
      @Qualifier("centralEntityManagerFactory") EntityManagerFactory centralEntityManagerFactory){
    return new JpaTransactionManager(centralEntityManagerFactory);
  }

  @Primary
  @Bean(name = "centralTransactionTemplate")
  public TransactionTemplate centralTransactionTemplate(PlatformTransactionManager centralTransactionManager) {
    return new TransactionTemplate(centralTransactionManager);
  }

}

