package edu.school21.repository.impl;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.model.Address;
import edu.school21.model.Client;
import edu.school21.repository.ClientRepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

  private final HikariDataSource dataSource;

  @Autowired
  public ClientRepositoryImpl(HikariDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void save(Client client) {
    try (Connection connection = dataSource.getConnection()) {
      String sql = "INSERT INTO clients VALUES (?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
//      preparedStatement.se
      preparedStatement.setString(2, client.getClientName());
      preparedStatement.setString(3, client.getClientSurname());
      preparedStatement.setDate(4, Date.valueOf(client.getBirthday()));
//      preparedStatement.set
      preparedStatement.setDate(5, Date.valueOf(client.getRegistrationDate()));
//      preparedStatement.set
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(UUID id) {

  }

  @Override
  public Optional<Client> findByNameAndSurName(String name, String surname) {
    return Optional.empty();
  }

  @Override
  public List<Client> findAll() {
    return null;
  }

  @Override
  public void update(UUID id, Address address) {

  }
}
