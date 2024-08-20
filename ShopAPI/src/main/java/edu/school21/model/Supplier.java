package edu.school21.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

  private UUID id;
  private String name;
  private UUID addressId;
  private String phoneNumber;

}