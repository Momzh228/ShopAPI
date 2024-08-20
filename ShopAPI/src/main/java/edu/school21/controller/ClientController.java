package edu.school21.controller;

import edu.school21.model.Address;
import edu.school21.model.Client;
import edu.school21.service.ClientService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

  private final ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping()
  public ResponseEntity<List<Client>> getByNameAndSurname(@RequestParam String name,
      @RequestParam String surname) {
    List<Client> clients = clientService.getByNameAndSurname(name, surname);
    return new ResponseEntity<>(clients, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Client>> getAll(@RequestParam(required = false) int page,
      @RequestParam(required = false) int pageSize) {
    List<Client> clients = clientService.getAll(page, pageSize);
    return new ResponseEntity<>(clients, HttpStatus.OK);
  }

  @PostMapping()
  public void create(@RequestBody Client client) {
    clientService.add(client);
  }

  @PatchMapping("/{id}")
  public void update(@PathVariable UUID id, @RequestBody Address newAddress) {
    clientService.update(id, newAddress);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    clientService.delete(id);
  }

}
