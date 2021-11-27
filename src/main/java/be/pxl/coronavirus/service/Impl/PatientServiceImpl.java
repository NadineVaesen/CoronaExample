package be.pxl.coronavirus.service.Impl;
import be.pxl.coronavirus.API.Resources.PatientDTO;
import be.pxl.coronavirus.API.Resources.PatientResource;
import be.pxl.coronavirus.domain.Doctor;
import be.pxl.coronavirus.domain.Patient;
import be.pxl.coronavirus.domain.VirusTest;
import be.pxl.coronavirus.repository.DoctorRepository;
import be.pxl.coronavirus.repository.PatientRepository;
import be.pxl.coronavirus.service.PatientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional
    public Long createPatient(PatientResource patientResource, int doctorNumber){
        Patient patient = mapPatientResourceToPatient(patientResource, doctorNumber);
        patientRepository.save(patient);
        return patient.getId();
    }

    @Override
    public List<PatientDTO> getPatientsByDoctor(int doctorNumber){
        Doctor doctor = findDoctor(doctorNumber);
        System.out.println(doctor);
        return patientRepository.findAllByDoctor(doctor).stream().map(PatientDTO::new).collect(Collectors.toList());
    }

    @Override
    public Patient findPatientByInsuranceNumber(int insuranceNumber) {
        return patientRepository.findPatientByInsuranceNumber(insuranceNumber);
    }

    @Override
    public List<PatientDTO> getPatientsByDoctorAndPositive(int doctorNumber) {
        System.out.println("in PatientServiceImpl");
        List<Patient> allPatients = patientRepository.findAllByDoctor(findDoctor(doctorNumber));
        for (Patient patient :
                allPatients) {
            System.out.println("patient: "+patient.getFirstName());
        }
        List<PatientDTO> allPatientsPositive = new ArrayList<>();
        for (Patient patient :
                allPatients) {
            VirusTest lastVirusTest = patient.getVirusTestList().stream().sorted().findFirst().get();
            if (lastVirusTest != null) {
                if (lastVirusTest.isPositive()) {
                    System.out.println("positive patient: " + patient.getFirstName());
                    allPatientsPositive.add(new PatientDTO(patient));
                }
            }
        }
        return allPatientsPositive;
    }


    private Doctor findDoctor(int doctorNumber) {
        return doctorRepository.findDoctorByDoctorNumber(doctorNumber);
    }

    private Patient mapPatientResourceToPatient(PatientResource patientResource, int doctorNumber) {
        Patient patient = new Patient();
        patient.setInsuranceNumber(patientResource.getInsuranceNumber());
        patient.setFirstName(patientResource.getFirstName());
        patient.setLastName(patientResource.getLastName());
        patient.setDoctor(doctorRepository.findDoctorByDoctorNumber(doctorNumber));
        return patient;
    }


}
