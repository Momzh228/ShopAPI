package edu.school21.controller;


import edu.school21.model.Image;
import edu.school21.service.ImageService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

  private final ImageService imageService;

  @Autowired
  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }


  @GetMapping("/{id}")
  public ResponseEntity<Image> getByImageId(@PathVariable UUID id) {
    Image image = imageService.getById(id);
    return new ResponseEntity<>(image, HttpStatus.OK);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<Image> getByProductId(@PathVariable UUID id) {
    Image image = imageService.getByProductId(id);
    return new ResponseEntity<>(image, HttpStatus.OK);
  }

  @PostMapping("/{id}")
  public void create(@PathVariable UUID id, @RequestBody byte[] imageBytes) {
    // todo на вход подается id клиента, а не image
    imageService.add(id, imageBytes);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable UUID id, @RequestBody byte[] imageBytes) {
    imageService.update(id, imageBytes);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    imageService.delete(id);
  }

}
