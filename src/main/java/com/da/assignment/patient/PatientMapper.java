package com.da.assignment.patient;


import org.mapstruct.Mapper;
import com.da.assignment.address.AddressMapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface PatientMapper {

    Patient voToDomain(PatientVO patientVO);

    PatientVO domainToVO(Patient patient);
}
