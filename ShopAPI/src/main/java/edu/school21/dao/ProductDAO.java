package edu.school21.dao;

import edu.school21.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDAO {

  public void add(Product product);

  public Optional<Product> getById(UUID id);

  public List<Product> getAll();

  public void update(UUID id, Integer quantity);

  public void delete(UUID id);

}
