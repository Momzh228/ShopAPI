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
public class Product {

  private UUID id;
  private String name;
  private Category category;
  private Double price;
  private Integer availableStock;
  private LocalDate lastUpdateDate;
  private UUID supplierId;
  private UUID imageId;

}
