package edu.school21.controller;

import edu.school21.model.Client;
import edu.school21.service.ClientService;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("/path")
  public void postClient(@RequestParam UUID id, @RequestParam String client_name,
      @RequestParam String client_surname, @RequestParam LocalDate birthday,
      @RequestParam String gender, @RequestParam LocalDate registration_date,
      @RequestParam UUID address_id) {

  }

  @GetMapping("/get")
  public Client getClientById(@RequestParam UUID id) {
//    return clientService.getClientById(id);
    return new Client();
  }

//  @DeleteMapping("/delete")
//  public Client deleteClientById(@RequestParam UUID id) {
//    return clientService.getClientById(id);
//  }

}
