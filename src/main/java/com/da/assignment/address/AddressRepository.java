package com.da.assignment.address;

import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.da.assignment.patient.Patient;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

  Set<Address> findByPatient(Patient patient);

}
