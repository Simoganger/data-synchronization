package com.sprintgether.datasynchro.config;

import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.sprintgether.datasynchro.model.central"})
@EnableJpaRepositories(
    basePackages = {"com.sprintgether.datasynchro.repository.central"},
    entityManagerFactoryRef = "centralEntityManagerFactory",
    transactionManagerRef = "centralTransactionManager"
)
public class CentralJpaConfig {

  @Bean
  public LocalContainerEntityManagerFactoryBean centralEntityManagerFactory(
      @Qualifier("centralDataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource)
        .packages("com.sprintgether.datasynchro.model.central")
        .build();
  }

  @Bean
  public PlatformTransactionManager centralTransactionManager(
      @Qualifier("centralEntityManagerFactory") LocalContainerEntityManagerFactoryBean centralEntityManagerFactory) {
    return new JpaTransactionManager(Objects.requireNonNull(centralEntityManagerFactory.getObject()));
  }

}
