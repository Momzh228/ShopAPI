package edu.school21.repository;

import edu.school21.model.Address;
import edu.school21.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Repository
public interface ClientRepository {

    void save(Client client);

    void delete(UUID id);

    Optional<Client> findByNameAndSurName(String name, String surname);

    List<Client> findAll();

    void update(UUID id, Address address);

}
