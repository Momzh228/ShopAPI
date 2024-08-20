package edu.school21.service;


import edu.school21.dao.AddressDAO;
import edu.school21.dao.ClientDAO;
import edu.school21.model.Address;
import edu.school21.model.Client;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  private final ClientDAO clientDAO;
  private final AddressDAO addressDAO;

  @Autowired
  public ClientService(ClientDAO clientDAO, AddressDAO addressDAO) {
    this.clientDAO = clientDAO;
    this.addressDAO = addressDAO;
  }

  public void add(Client client) {
    clientDAO.add(client);
  }

  public List<Client> getAll(int page, int pageSize) {
    return clientDAO.getAll(page, pageSize);
  }

  public List<Client> getByNameAndSurname(String name, String surname) {
    return clientDAO.getByNameAndSurname(name, surname);
  }

  public void update(UUID id, Address address) {
    addressDAO.add(address);
    clientDAO.update(id, address.getId());
  }

  public void delete(UUID id) {
    clientDAO.delete(id);
  }

}
