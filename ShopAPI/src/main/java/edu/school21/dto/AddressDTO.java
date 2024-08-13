package edu.school21.dto;

import edu.school21.model.Address;

public class AddressDTO {

  public static Address toEntity(AddressDTO dto) {
    if (dto == null) {
      return null;
    }
    Address entity = new Address();
//    entity.setId(dto.);
    return entity;
  }

}
