package edu.school21.service;


import edu.school21.dao.ProductDAO;
import edu.school21.exception.EntityNotFoundException;
import edu.school21.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductDAO productDAO;

  @Autowired
  public ProductService(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  public void add(Product product) {
    productDAO.add(product);
  }

  public Product getById(UUID id) {
    Optional<Product> product = productDAO.getById(id);
    return product.orElseThrow(() -> new EntityNotFoundException("Product not found"));
  }

  public List<Product> getAllProducts() {
    return productDAO.getAll();
  }


  public void update(UUID id, int quantity) {
    productDAO.update(id, quantity);
  }

  public void delete(UUID id) {
    productDAO.delete(id);
  }

}
