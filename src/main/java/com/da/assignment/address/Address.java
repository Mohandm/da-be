package com.da.assignment.address;

import static javax.persistence.FetchType.LAZY;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import com.da.assignment.patient.Patient;

@Entity
public class Address {

    @Id
    @SequenceGenerator(name = "address_generator", sequenceName = "address_seq")
    @GeneratedValue(generator = "address_generator")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String country;

    private String postalCode;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Patient getPatient() {
      return patient;
    }

    public void setPatient(Patient patient) {
      this.patient = patient;
    }

    public String getAddressLine1() {
      return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
      this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
      return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
      this.addressLine2 = addressLine2;
    }

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public String getPostalCode() {
      return postalCode;
    }

    public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
    }

}
