package edu.school21.service;

import edu.school21.dao.AddressDAO;
import edu.school21.dao.SupplierDAO;
import edu.school21.exception.EntityNotFoundException;
import edu.school21.model.Address;
import edu.school21.model.Supplier;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  private final SupplierDAO supplierDAO;
  private final AddressDAO addressDAO;

  @Autowired
  public SupplierService(SupplierDAO supplierDAO, AddressDAO addressDAO) {
    this.supplierDAO = supplierDAO;
    this.addressDAO = addressDAO;
  }

  public void add(Supplier supplier) {
    supplierDAO.add(supplier);
  }

  public Supplier getById(UUID id) {
    Optional<Supplier> Supplier = supplierDAO.getById(id);
    if (Supplier.isPresent()) {
      return Supplier.get();
    }
    throw new EntityNotFoundException("Supplier not found");
  }

  public List<Supplier> getAll() {
    return supplierDAO.getAll();
  }

  public void update(UUID id, Address address) {
    supplierDAO.update(id, address);
  }

  public void delete(UUID id) {
    supplierDAO.delete(id);
  }

}
