package com.sprintgether.datasynchro.config;

import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.sprintgether.datasynchro.model.local"})
@EnableJpaRepositories(
    basePackages = {"com.sprintgether.datasynchro.repository.local"},
    entityManagerFactoryRef = "localEntityManagerFactory",
    transactionManagerRef = "localTransactionManager"
)
public class LocalJpaConfig {

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean localEntityManagerFactory(
      @Qualifier("localDataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource)
        .packages("com.sprintgether.datasynchro.model.local")
        .build();
  }

  @Primary
  @Bean
  public PlatformTransactionManager localTransactionManager(
      @Qualifier("localEntityManagerFactory") LocalContainerEntityManagerFactoryBean localEntityManagerFactory) {
    return new JpaTransactionManager(Objects.requireNonNull(localEntityManagerFactory.getObject()));
  }

}
