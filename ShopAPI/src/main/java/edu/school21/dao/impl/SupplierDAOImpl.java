package edu.school21.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.dao.SupplierDAO;
import edu.school21.exception.DAOException;
import edu.school21.model.Address;
import edu.school21.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class SupplierDAOImpl implements SupplierDAO {

  private final HikariDataSource dataSource;

  @Autowired
  public SupplierDAOImpl(HikariDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void save(Supplier supplier) throws DAOException {
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO suppliers (id, name, address_id, phone_number) VALUES (?,?,?,?)");
      statement.setObject(1, supplier.getId());
      statement.setString(2, supplier.getName());
      statement.setObject(3, supplier.getAddress_id());
      statement.setString(4, supplier.getPhone_number());
      statement.executeUpdate();
      statement.close();
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public void update(UUID id, Address newAddress) throws DAOException {
    try (Connection connection = dataSource.getConnection(); PreparedStatement statementAddress = connection.prepareStatement(
        "UPDATE address SET id = ?, country = ?, city = ?, street = ? WHERE id = ?")) {
      statementAddress.setObject(1, newAddress.getId());
      statementAddress.setString(2, newAddress.getCountry());
      statementAddress.setString(3, newAddress.getCity());
      statementAddress.setString(4, newAddress.getStreet());
      statementAddress.setObject(5, id);
      statementAddress.executeUpdate();
      try (PreparedStatement statementSupplier = connection.prepareStatement(
          "UPDATE suppliers SET address_id = ? WHERE id = ?")) {
        statementSupplier.setObject(1, newAddress.getId());
        statementSupplier.setObject(2, id);
        statementSupplier.executeUpdate();
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }

  }

  @Override
  public void delete(UUID id) throws DAOException {
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        "DELETE FROM suppliers WHERE id = ?");) {
      statement.setObject(1, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public List<Supplier> findAll() throws DAOException {
    List<Supplier> suppliers = new ArrayList<>();
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        "SELECT * FROM suppliers"); ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        UUID id = resultSet.getObject("id", UUID.class);
        String name = resultSet.getString("name");
        UUID address = resultSet.getObject("address_id", UUID.class);
        String phone_number = resultSet.getString("phone_number");
        suppliers.add(new Supplier(id, name, address, phone_number));
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
    return suppliers;
  }

  @Override
  public Supplier findById(UUID id) throws DAOException {
    Supplier supplier = new Supplier();
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        "SELECT * FROM suppliers WHERE id = ?")) {
      statement.setObject(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        throw new DAOException("Supplier with id " + id + " not found");
      }
      while (resultSet.next()) {
        UUID supplier_id = resultSet.getObject("id", UUID.class);
        String name = resultSet.getString("name");
        UUID address_id = resultSet.getObject("address_id", UUID.class);
        String phone_number = resultSet.getString("phone_number");
        supplier.setId(supplier_id);
        supplier.setName(name);
        supplier.setAddress_id(address_id);
        supplier.setPhone_number(phone_number);
      }
      resultSet.close();
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
    return supplier;
  }
}
