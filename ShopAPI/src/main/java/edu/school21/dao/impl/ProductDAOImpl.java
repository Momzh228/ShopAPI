package edu.school21.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.dao.ProductDAO;
import edu.school21.exception.DAOException;
import edu.school21.exception.EntityNotFoundException;
import edu.school21.model.Category;
import edu.school21.model.Product;
import java.sql.Connection;
import java.sql.Date;
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

@Repository
@Component
public class ProductDAOImpl implements ProductDAO {

  private final HikariDataSource dataSource;

  @Autowired
  public ProductDAOImpl(HikariDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void add(Product product) {
    String sql = "INSERT INTO products (id, name, category, price, available_stock, last_update_date, supplier_id, image_id) VALUES (?,?,?,?,?,?,?,?)";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, product.getId());
      statement.setString(2, product.getName());
      statement.setObject(3, product.getCategory());
      statement.setDouble(4, product.getPrice());
      statement.setInt(5, product.getAvailableStock());
      statement.setDate(6, Date.valueOf(product.getLastUpdateDate()));
      statement.setObject(7, product.getSupplierId());
      statement.setObject(8, product.getImageId());
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Product not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public void update(UUID id, Integer quantity) throws DAOException {
    String sql = "UPDATE products SET available_stock = (available_stock - ?) WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Product not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public Optional<Product> getById(UUID id) {
    String sql = "SELECT * FROM products WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Product product = buildProduct(resultSet);
        return Optional.of(product);
      }
      resultSet.close();
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
    return Optional.empty();
  }

  @Override
  public List<Product> getAll() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM products";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql); ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        Product product = buildProduct(resultSet);
        products.add(product);
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
    return products;
  }

  private Product buildProduct(ResultSet resultSet) throws SQLException {
    Product product = new Product();
    product.setId(resultSet.getObject("id", UUID.class));
    product.setName(resultSet.getString("name"));
    product.setCategory(resultSet.getObject("category", Category.class));
    product.setPrice(resultSet.getDouble("price"));
    product.setAvailableStock(resultSet.getInt("available_stock"));
    product.setLastUpdateDate(resultSet.getDate("last_update_date").toLocalDate());
    product.setSupplierId(resultSet.getObject("supplier_id", UUID.class));
    product.setImageId(resultSet.getObject("image_id", UUID.class));
    return product;
  }

  @Override
  public void delete(UUID id) {
    String sql = "DELETE FROM products WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, id);
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Product not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }
}
