package edu.school21.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.dao.ImageDAO;
import edu.school21.exception.DAOException;
import edu.school21.exception.EntityNotFoundException;
import edu.school21.model.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDAOImpl implements ImageDAO {

  private final HikariDataSource dataSource;

  @Autowired
  public ImageDAOImpl(HikariDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void add(Image image) {
    String sql = "INSERT INTO images (id, image) VALUES(?,?)";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, image.getId());
      statement.setBytes(2, image.getImage());
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Image not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public Optional<Image> getById(UUID id) {
    String sql = "SELECT * FROM images WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Image image = new Image();
        image.setId(resultSet.getObject("id", UUID.class));
        image.setImage(resultSet.getBytes("image"));
        return Optional.of(image);
      }
      resultSet.close();
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
    return Optional.empty();
  }

  @Override
  public void update(UUID id, byte[] image) {
    String sql = "UPDATE images (image) VALUES(?) WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setBytes(1, image);
      statement.setObject(2, id);
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Image not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }

  @Override
  public void delete(UUID id) {
    String sql = "DELETE FROM images WHERE id = ?";
    try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
        sql)) {
      statement.setObject(1, id);
      int affectedRows = statement.executeUpdate();
      if (affectedRows == 0) {
        throw new EntityNotFoundException("Image not found");
      }
    } catch (SQLException e) {
      throw new DAOException(e.getMessage());
    }
  }


}
