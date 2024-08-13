package edu.school21.repository;

import edu.school21.model.Image;

import java.util.Optional;
import java.util.UUID;

public interface ImagesRepository {

    void save(Image image);
    void update(Image image);
    void delete(UUID id);
    Optional<Image> findByProductId(UUID id);
    Optional<Image> findByImageId(UUID id);
}
