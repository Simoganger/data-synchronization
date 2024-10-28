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
public class LocalDataSourceConfig {

  @Primary
  @Bean(name = "localDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.local")
  public DataSource localDatasource() {
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name = "localEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean localEntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("localDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource)
        .packages("com.sprintgether.datasynchro.model.local")
        .persistenceUnit("local")
        .build();
  }

  @Primary
  @Bean(name = "localTransactionManager")
  public PlatformTransactionManager localTransactionManager(
      @Qualifier("localEntityManagerFactory") EntityManagerFactory localEntityManagerFactory){
    return new JpaTransactionManager(localEntityManagerFactory);
  }

  @Primary
  @Bean(name = "localTransactionTemplate")
  public TransactionTemplate localTransactionTemplate(PlatformTransactionManager localTransactionManager) {
    return new TransactionTemplate(localTransactionManager);
  }

}
