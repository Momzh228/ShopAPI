package edu.school21.dao;

import edu.school21.model.Address;
import edu.school21.model.Supplier;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierDAO {

  public void add(Supplier supplier);

  public Optional<Supplier> getById(UUID id);

  public List<Supplier> getAll();

  public void update(UUID id, Address newAddress);

  public void delete(UUID id);

}
