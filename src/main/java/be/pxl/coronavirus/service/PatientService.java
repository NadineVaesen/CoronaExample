package be.pxl.coronavirus.service;

import be.pxl.coronavirus.API.Resources.PatientDTO;
import be.pxl.coronavirus.API.Resources.PatientResource;
import be.pxl.coronavirus.domain.Patient;

import javax.transaction.Transactional;
import java.util.List;

public interface PatientService {
    @Transactional
    Long createPatient(PatientResource patientResource, int doctorNumber);

    List<PatientDTO> getPatientsByDoctor(int doctorNumber);
    Patient findPatientByInsuranceNumber(int insuranceNumber);
    List<PatientDTO> getPatientsByDoctorAndPositive(int doctorNumber);
}
