package edu.school21.repository.impl;

import edu.school21.model.Image;
import edu.school21.repository.ImagesRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class ImagesRepositoryImpl implements ImagesRepository {

  private final DataSource dataSource;

  @Autowired
  public ImagesRepositoryImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void save(Image image) {
    try (Connection connection = dataSource.getConnection()) {
      final String DELETE_QUERY = "DELETE FROM images WHERE id = ?";

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(Image image) {

  }

  @Override
  public void delete(UUID id) {
    try (Connection connection = dataSource.getConnection()) {
      final String DELETE_QUERY = "DELETE FROM images WHERE id = ?";

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Optional<Image> findByImageId(UUID id) {
    return Optional.empty();
  }

  @Override
  public Optional<Image> findByProductId(UUID id) {
    return Optional.empty();
  }
}
