package edu.school21.dao;

import edu.school21.model.Client;
import java.util.List;
import java.util.UUID;

public interface ClientDAO {

  public void add(Client client);

  public List<Client> getByNameAndSurname(String name, String surname);

  public List<Client> getAll(int page, int pageSize);

  public void update(UUID id, UUID addressID);

  public void delete(UUID id);

}
