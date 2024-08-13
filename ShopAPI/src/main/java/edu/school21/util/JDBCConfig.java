package edu.school21.util;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JDBCConfig {

  @Bean
  public HikariDataSource dataSource() {
    HikariDataSource ds = new HikariDataSource();
    String URL = "jdbc:postgresql://localhost:5432/postgres";
    ds.setJdbcUrl(URL);
    String USER = "postgres";
    ds.setUsername(USER);
    String PASSWORD = "postgres";
    ds.setPassword(PASSWORD);
    return ds;
  }

}
