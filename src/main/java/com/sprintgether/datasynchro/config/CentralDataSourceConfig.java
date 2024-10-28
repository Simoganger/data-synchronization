package com.sprintgether.datasynchro.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class CentralDataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.central")
  public DataSourceProperties centralDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public DataSource centralDataSource() {
    return centralDataSourceProperties()
        .initializeDataSourceBuilder()
        .build();
  }

  @Bean
  public JdbcTemplate centralJdbcTemplate(@Qualifier("centralDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

}
