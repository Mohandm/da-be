package com.da.assignment.address;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

  Address voToDomain(AddressVO addressVO);

  AddressVO domainToVO(Address address);

}
