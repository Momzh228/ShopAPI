package edu.school21.dao;

import edu.school21.model.Image;
import java.util.Optional;
import java.util.UUID;

public interface ImageDAO {

  public void add(Image image);

  public Optional<Image> getById(UUID id);

  public void update(UUID id, byte[] image);

  public void delete(UUID id);

}
