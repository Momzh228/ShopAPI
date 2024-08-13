package edu.school21.service;

import edu.school21.dao.SupplierDAO;
import edu.school21.dao.impl.SupplierDAOImpl;
import edu.school21.exception.DAOException;
import edu.school21.model.Address;
import edu.school21.model.Supplier;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierService {

  private final SupplierDAO supplierDAO;

  @Autowired
  public SupplierService(SupplierDAOImpl supplierDAO) {
    this.supplierDAO = supplierDAO;
  }

  public void addSupplier(Supplier supplier) {
    try {
      supplierDAO.save(supplier);
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateSupplier(UUID id, Address address) {
    try {
      supplierDAO.update(id, address);
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteSupplier(UUID id) {
    try {
      supplierDAO.delete(id);
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Supplier> getAllSuppliers() {
    try {
      return supplierDAO.findAll();
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

  public Supplier getSupplier(UUID id) {
    try {
      return supplierDAO.findById(id);
    } catch (DAOException e) {
      throw new RuntimeException(e);
    }
  }

}
