package edu.school21.dao;

import edu.school21.exception.DAOException;
import edu.school21.model.Image;

import java.util.UUID;

public interface ImageDAO {

    public void add(Image image) throws DAOException;
    public void update(UUID id, byte[] image) throws DAOException;
    public void delete(UUID id) throws DAOException;
    public Image getByProductId(UUID id);
    public Image getByImageId(UUID id) throws DAOException;


}
