package edu.school21.repository;

import edu.school21.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    void save(Product product);

    void update(UUID id, Integer quantity);

    Optional<Product> findById(UUID id);

    List<Product> findAll();

    void delete(UUID id);

}
