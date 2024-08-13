package edu.school21.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.dao.ImageDAO;
import edu.school21.exception.DAOException;
import edu.school21.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
@Repository
public class ImageDAOImpl implements ImageDAO {

    private final HikariDataSource dataSource;

    @Autowired
    public ImageDAOImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Image image) throws DAOException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO images (id, image) VALUES(?,?)")) {
            statement.setObject(1, image.getId());
            statement.setBytes(2, image.getImage());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void update(UUID id, byte[] image) throws DAOException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE images (image) VALUES(?) WHERE id = ?")) {
            statement.setBytes(1, image);
            statement.setObject(2, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Image with id " + id + " not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) throws DAOException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM images WHERE id = ?")) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public Image getByProductId(UUID id) {
        return null;
    }

    @Override
    public Image getByImageId(UUID id) throws DAOException {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT image FROM images WHERE id = ?")) {
            Image image = new Image();
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DAOException("Image with id " + id + " not found");
            }
            image.setImage(resultSet.getBytes("image"));
            return image;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
