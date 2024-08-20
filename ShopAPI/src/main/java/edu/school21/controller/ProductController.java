package edu.school21.controller;

import edu.school21.model.Product;
import edu.school21.service.ProductService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable UUID id) {
    Product product = productService.getById(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    List<Product> products = productService.getAllProducts();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @PostMapping
  public void add(@RequestBody Product product) {
    productService.add(product);
  }

  @PatchMapping("/{id}")
  public void updateProduct(@PathVariable UUID id, @RequestParam int quantity) {
    productService.update(id, quantity);
  }


  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable UUID id) {
    productService.delete(id);
  }

}
