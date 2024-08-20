package edu.school21.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.dao.AddressDAO;
import edu.school21.exception.DAOException;
import edu.school21.model.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AddressDAOImpl implements AddressDAO {

  private final HikariDataSource dataSource;

  @Autowired
  public AddressDAOImpl(HikariDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void add(Address address) {
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        "INSERT INTO addresses (id, country, city, street) VALUES (?,?,?,?)")) {
      statement.setObject(1, address.getId());
      statement.setString(2, address.getCountry());
      statement.setString(3, address.getCity());
      statement.setString(4, address.getStreet());
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new DAOException("Address not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }
}
