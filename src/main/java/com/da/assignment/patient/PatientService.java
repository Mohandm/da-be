package com.da.assignment.patient;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.da.assignment.exception.RecordNotFoundException;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    private PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Transactional
    public PatientVO create(PatientVO patientVO) {
      patientVO.setStatus(PatientStatus.ACTIVE);
        Patient patient = patientMapper.voToDomain(patientVO);
        patient.getAddresses().stream().forEach(address -> address.setPatient(patient));
        return patientMapper.domainToVO(patientRepository.save(patient));
    }

    public PatientVO getPatient(Long id) {
      Patient patient = patientRepository.findById(id)
          .orElseThrow(() -> new RecordNotFoundException("Patient Not found with id - " + id));
        return patientMapper.domainToVO(patient);
    }

    @Transactional
    public PatientVO update(PatientVO patientVO, Long id) {
      patientVO.setId(id);
      Patient patient = patientMapper.voToDomain(patientVO);
      patient.getAddresses().stream().forEach(address -> address.setPatient(patient));
      return patientMapper.domainToVO(patientRepository.save(patient));
    }

    @Transactional
    public void softDelete(Long id) {
      patientRepository.updateStatus(PatientStatus.DELETED, id);
    }

    public Page<PatientVO> getPatients(int size, int page) {
      return patientRepository.findAll(PageRequest.of(page, size)).map(patientMapper::domainToVO);
    }

    @Transactional
    public void deletePatient(Long id) {
      patientRepository.deleteById(id);
    }
}
