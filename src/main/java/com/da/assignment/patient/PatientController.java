package com.da.assignment.patient;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/patients")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientVO> createPatient(@RequestBody PatientVO patientVO) {
        return  ResponseEntity.accepted().body(patientService.create(patientVO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientVO> updatePatient(@RequestBody PatientVO patientVO, @PathVariable Long id) {
        return  ResponseEntity.accepted().body(patientService.update(patientVO, id));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> softDeletePatient(@RequestBody PatientVO patientVO,
        @PathVariable Long id) {
      patientService.softDelete(id);
      return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientVO> getPatient(@PathVariable Long id) {
        return  ResponseEntity.accepted().body(patientService.getPatient(id));
    }

    @GetMapping
    public ResponseEntity<Page<PatientVO>> getPatients(@RequestParam int size,
        @RequestParam int page) {
        return  ResponseEntity.accepted().body(patientService.getPatients(size, page));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
      patientService.deletePatient(id);
      return ResponseEntity.ok().build();
    }
}
