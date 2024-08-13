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
  private Integer available_stock;
  private LocalDate last_update_date;
  private UUID supplier_id;
  private UUID image_id;

}
