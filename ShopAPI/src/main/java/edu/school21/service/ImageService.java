package edu.school21.service;


import edu.school21.dao.ImageDAO;
import edu.school21.dao.ProductDAO;
import edu.school21.exception.DAOException;
import edu.school21.exception.EntityNotFoundException;
import edu.school21.model.Image;
import edu.school21.model.Product;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

  private final ImageDAO imageDAO;
  private final ProductDAO productDAO;

  @Autowired
  public ImageService(ImageDAO imageDAO, ProductDAO productDAO) {
    this.imageDAO = imageDAO;
    this.productDAO = productDAO;
  }

  public void add(UUID id, byte[] imageBytes) {
    UUID uuid = UUID.randomUUID();
    Image image = new Image(uuid, imageBytes);
    // todo
    imageDAO.add(image);
  }

  public Image getByProductId(UUID id) {
    try {
      Optional<Product> product = productDAO.getById(id);
      if (product.isPresent()) {
        UUID imageId = product.get().getImageId();
        Optional<Image> image = imageDAO.getById(imageId);
        if (image.isPresent()) {
          return image.get();
        } else {
          throw new EntityNotFoundException("Image not found");
        }
      } else {
        throw new EntityNotFoundException("Product not found");
      }
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

  public Image getById(UUID id) {
    Optional<Image> image = imageDAO.getById(id);
    if (image.isPresent()) {
      return image.get();
    }
    throw new EntityNotFoundException("Image not found");
  }

  public void update(UUID id, byte[] imageBytes) {
    imageDAO.update(id, imageBytes);
  }

  public void delete(UUID id) {
    imageDAO.delete(id);
  }

}
