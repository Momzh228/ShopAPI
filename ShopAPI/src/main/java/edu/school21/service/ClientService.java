package edu.school21.service;


import edu.school21.model.Client;
import edu.school21.repository.impl.ClientRepositoryImpl;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  private final ClientRepositoryImpl clientRepository;

  @Autowired
  public ClientService(ClientRepositoryImpl clientRepository) {
    this.clientRepository = clientRepository;
  }

  public void addClient(Client client) {
    clientRepository.save(client);
  }

}
