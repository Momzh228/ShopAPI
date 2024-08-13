package edu.school21.repository;

import edu.school21.model.Supplier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository {

    void save(Supplier supplier);

//    void update(Supplier supplier);

    void delete(UUID id);

    List<Supplier> findAll();

    Optional<Supplier> findById(UUID id);


}
