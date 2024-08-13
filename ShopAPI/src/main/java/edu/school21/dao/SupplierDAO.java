package edu.school21.dao;

import edu.school21.exception.DAOException;
import edu.school21.model.Address;
import edu.school21.model.Supplier;
import java.util.List;
import java.util.UUID;

public interface SupplierDAO {

  public void save(Supplier supplier) throws DAOException;
  public void update(UUID id, Address newAddress) throws DAOException;
  public void delete(UUID id) throws DAOException;
  public List<Supplier> findAll() throws DAOException;
  public Supplier findById(UUID id) throws DAOException;

}
