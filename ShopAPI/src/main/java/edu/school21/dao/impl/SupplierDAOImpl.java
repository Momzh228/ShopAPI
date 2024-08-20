package edu.school21.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.dao.SupplierDAO;
import edu.school21.exception.DAOException;
import edu.school21.exception.EntityNotFoundException;
import edu.school21.model.Address;
import edu.school21.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
  public void add(Supplier supplier) {
    String sql = "INSERT INTO suppliers (id, name, address_id, phone_number) VALUES (?,?,?,?)";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, supplier.getId());
      statement.setString(2, supplier.getName());
      statement.setObject(3, supplier.getAddressId());
      statement.setString(4, supplier.getPhoneNumber());
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Suppier not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public List<Supplier> getAll() {
    List<Supplier> suppliers = new ArrayList<>();
    String sql = "SELECT * FROM suppliers";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql); ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        Supplier supplier = buildSupplier(resultSet);
        suppliers.add(supplier);
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
    return suppliers;
  }

  @Override
  public Optional<Supplier> getById(UUID id) {
    String sql = "SELECT * FROM suppliers WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Supplier supplier = buildSupplier(resultSet);
        return Optional.of(supplier);
      }
      resultSet.close();
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
    return Optional.empty();
  }

  private Supplier buildSupplier(ResultSet resultSet) throws SQLException {
    Supplier supplier = new Supplier();
    supplier.setId(resultSet.getObject("id", UUID.class));
    supplier.setName(resultSet.getString("name"));
    supplier.setAddressId(resultSet.getObject("address_id", UUID.class));
    supplier.setPhoneNumber(resultSet.getString("phone_number"));
    return supplier;
  }

  @Override
  public void update(UUID id, Address newAddress) {
    String sql = "UPDATE address SET id = ?, country = ?, city = ?, street = ? WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statementAddress = connection.prepareStatement(
        sql)) {
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
        int affectedRows = statementSupplier.executeUpdate();
        if (affectedRows == 0) {
          throw new EntityNotFoundException("Suppier not found");
        }
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public void delete(UUID id) {
    String sql = "DELETE FROM suppliers WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, id);
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Suppier not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

}
