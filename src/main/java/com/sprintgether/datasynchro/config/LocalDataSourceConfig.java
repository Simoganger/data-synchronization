package com.sprintgether.datasynchro.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class LocalDataSourceConfig {

  @Primary
  @Bean
  @ConfigurationProperties("spring.datasource.local")
  public DataSourceProperties localDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean
  public DataSource localDataSource() {
    return localDataSourceProperties()
        .initializeDataSourceBuilder()
        .build();
  }

  @Primary
  @Bean
  public JdbcTemplate localJdbcTemplate(@Qualifier("localDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

}
