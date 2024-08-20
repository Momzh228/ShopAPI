package edu.school21.dto;


import edu.school21.model.Address;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientUpdateDTO {

  private UUID id;
  private Address address;
}
