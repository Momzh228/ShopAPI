package edu.school21.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.dao.ClientDAO;
import edu.school21.exception.DAOException;
import edu.school21.exception.EntityNotFoundException;
import edu.school21.model.Client;
import edu.school21.model.Gender;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOImpl implements ClientDAO {

  private final HikariDataSource dataSource;

  @Autowired
  public ClientDAOImpl(HikariDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void add(Client client) {
    String sql = "INSERT INTO clients (id, client_name, client_surname, birthday, gender, registration_date, address_id) VALUES (?,?,?,?,?,?,?)";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, client.getId());
      statement.setString(2, client.getClientName());
      statement.setString(3, client.getClientSurname());
      statement.setDate(4, Date.valueOf(client.getBirthday()));
      statement.setObject(5, client.getGender());
      statement.setDate(6, Date.valueOf(client.getRegistrationDate()));
      statement.setObject(7, client.getAddressId());
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Client not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public List<Client> getByNameAndSurname(String name, String surname) {
    String sql = "SELECT * FROM clients WHERE client_name = ? AND client_surname = ?";
    return getListClients(sql, name, surname);
  }

  @Override
  public List<Client> getAll(int page, int pageSize) {
    StringBuilder sql = new StringBuilder("SELECT * FROM clients");
    if (page != 0 && pageSize != 0) {
      sql.append(" LIMIT ? OFFSET ?");
      return getListClients(sql.toString(), pageSize, (page - 1) * pageSize);
    }
    return getListClients(sql.toString());
  }

  private List<Client> getListClients(String sql, Object... params) {
    List<Client> clients = new ArrayList<>();
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      for (int i = 0; i < params.length; i++) {
        statement.setObject(i + 1, params[i]);
      }
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        UUID id = resultSet.getObject("id", UUID.class);
        String clientName = resultSet.getString("client_name");
        String clientSurname = resultSet.getString("client_surname");
        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
        Gender gender = resultSet.getObject("gender", Gender.class);
        LocalDate registrationDate = resultSet.getDate("registration_date").toLocalDate();
        UUID addressId = resultSet.getObject("address_id", UUID.class);
        Client client = new Client(id, clientName, clientSurname, birthday, gender,
            registrationDate, addressId);
        clients.add(client);
      }
      resultSet.close();
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
    return clients;
  }

  @Override
  public void update(UUID id, UUID addressId) {
    String sql = "UPDATE clients SET address_id = ? WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, addressId);
      statement.setObject(2, id);
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Client not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public void delete(UUID id) {
    String sql = "DELETE FROM clients WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, id);
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Client not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }
}