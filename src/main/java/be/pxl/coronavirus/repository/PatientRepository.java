package be.pxl.coronavirus.repository;

import be.pxl.coronavirus.domain.Doctor;
import be.pxl.coronavirus.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findAllByDoctor(Doctor doctor);
    Patient findPatientByInsuranceNumber(int insuranceNumber);

}