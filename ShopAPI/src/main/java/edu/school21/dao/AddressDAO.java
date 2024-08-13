package edu.school21.dao;

import edu.school21.model.Address;
import java.util.List;

public interface AddressDAO {

  public void save(Address address);
  public Address findById(int id);
  public List<Address> findAll();

}
