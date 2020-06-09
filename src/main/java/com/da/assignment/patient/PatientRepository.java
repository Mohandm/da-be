package com.da.assignment.patient;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientRepository extends PagingAndSortingRepository<Patient,Long> {

  @Query("from Patient p left join fetch p.addresses where p.id =:id")
  Optional<Patient> findById(Long id);

  @Modifying
  @Query("update Patient set status =:status where id =:id")
  void updateStatus(PatientStatus status, Long id);
}
