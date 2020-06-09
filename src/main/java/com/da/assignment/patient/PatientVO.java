package com.da.assignment.patient;

import java.util.Set;
import com.da.assignment.address.AddressVO;

public class PatientVO {
    private Long id;

    private String firstName;

    private  String lastName;

    private String contactNumber;

    private PatientStatus status;

    private Set<AddressVO> addresses;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getContactNumber() {
      return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
      this.contactNumber = contactNumber;
    }

    public Set<AddressVO> getAddresses() {
      return addresses;
    }

    public void setAddresses(Set<AddressVO> addresses) {
      this.addresses = addresses;
    }

    public PatientStatus getStatus() {
      return status;
    }

    public void setStatus(PatientStatus status) {
      this.status = status;
    }
}
