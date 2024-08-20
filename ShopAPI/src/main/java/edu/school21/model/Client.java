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
  private String clientName;
  private String clientSurname;
  private LocalDate birthday;
  private Gender gender;
  private LocalDate registrationDate;
  private UUID addressId;
}
