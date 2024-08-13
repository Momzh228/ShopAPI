package edu.school21.model;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

  private UUID id;
  private String client_name;
  private String client_surname;
  private LocalDate birthday;
  private Gender gender;
  private LocalDate registration_date;
  private UUID address_id;
}
