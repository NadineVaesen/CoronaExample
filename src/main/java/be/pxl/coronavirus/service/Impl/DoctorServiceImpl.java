package be.pxl.coronavirus.service.Impl;

import be.pxl.coronavirus.API.Resources.DoctorResource;
import be.pxl.coronavirus.domain.Doctor;
import be.pxl.coronavirus.repository.DoctorRepository;
import be.pxl.coronavirus.service.DoctorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional
    public Long createDoctor(DoctorResource doctorResource) {
        Doctor doctor = mapToDoctor(doctorResource);
        doctorRepository.save(doctor);
        return doctor.getId();
    }

    private static Doctor mapToDoctor(DoctorResource doctorResource){
        Doctor doctor = new Doctor();
        doctor.setDoctorNumber(doctorResource.getDoctorNumber());
        doctor.setFirstName(doctorResource.getFirstName());
        doctor.setLastName(doctorResource.getLastName());
        return doctor;
    }
}
