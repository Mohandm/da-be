package com.da.assignment.patient;

import static javax.persistence.EnumType.STRING;
import java.time.Instant;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.da.assignment.address.Address;

@Entity
@EnableJpaAuditing
public class Patient {

  @Id
  @SequenceGenerator(name = "patient_generator", sequenceName = "patient_seq")
  @GeneratedValue(generator = "patient_generator")
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdOn;

    @LastModifiedDate
    private Instant updatedOn;

    private String firstName;

    private  String lastName;

    private String contactNumber;

    @Enumerated(STRING)
    private PatientStatus status;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Instant getCreatedOn() {
      return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
      this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
      return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
      this.updatedOn = updatedOn;
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

    public Set<Address> getAddresses() {
      return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
      this.addresses = addresses;
    }

    public PatientStatus getStatus() {
      return status;
    }

    public void setStatus(PatientStatus status) {
      this.status = status;
    }
}
