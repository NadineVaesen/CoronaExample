package be.pxl.coronavirus.service.Impl;

import be.pxl.coronavirus.API.Resources.VirusTestDTO;
import be.pxl.coronavirus.API.Resources.VirusTestResource;
import be.pxl.coronavirus.domain.Patient;
import be.pxl.coronavirus.domain.VirusTest;
import be.pxl.coronavirus.repository.PatientRepository;
import be.pxl.coronavirus.repository.VirusTestRepository;
import be.pxl.coronavirus.service.VirusTestService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusTestServiceImpl implements VirusTestService {

    private VirusTestRepository virusTestRepository;
    private PatientRepository patientRepository;

    public VirusTestServiceImpl(VirusTestRepository virusTestRepository, PatientRepository patientRepository) {
        this.virusTestRepository = virusTestRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public Long createVirusTest(VirusTestResource virusTestResource){
        VirusTest virusTest = mapResourceToVirusTest(virusTestResource);
        virusTestRepository.save(virusTest);
        return virusTest.getId();
    }

    @Override
    public List<VirusTestDTO> findVirusTestsForPatient(Patient patient) {
        List<VirusTest> list = virusTestRepository.findAllByPatient(patient);
        return list.stream().map(VirusTestDTO::new).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void deleteVirusTestById(Long id) {
        virusTestRepository.deleteVirusTestById(id);
    }

    @Override
    @Transactional
    public VirusTestDTO updateVirusTest(Long virusTestId, VirusTestResource virusTestResource) {
        VirusTest virusTest = new VirusTest();
        virusTest.setId(virusTestId);
        virusTest.setPositive(virusTestResource.isPositive());
        virusTest.setPatient(patientRepository.findPatientByInsuranceNumber(virusTestResource.getInsuranceNumber()));
        virusTestRepository.save(virusTest);
        return mapToVirusTestDTO(virusTest);
    }



    private VirusTestDTO mapToVirusTestDTO(VirusTest virusTest) {
        return new VirusTestDTO(virusTest);
    }

    private VirusTest mapResourceToVirusTest(VirusTestResource virusTestResource) {
        VirusTest virusTest = new VirusTest();
        virusTest.setPatient(patientRepository.findPatientByInsuranceNumber(virusTestResource.getInsuranceNumber()));
        virusTest.setPositive(virusTestResource.isPositive());
        return virusTest;
    }
}
