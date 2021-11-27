package be.pxl.coronavirus.service;

import be.pxl.coronavirus.API.Resources.VirusTestDTO;
import be.pxl.coronavirus.API.Resources.VirusTestResource;
import be.pxl.coronavirus.domain.Patient;
import be.pxl.coronavirus.domain.VirusTest;

import javax.transaction.Transactional;
import java.util.List;

public interface VirusTestService {

    @Transactional
    Long createVirusTest(VirusTestResource virusTestResource);
    List<VirusTestDTO> findVirusTestsForPatient(Patient patient);
    void deleteVirusTestById(Long id);
    VirusTestDTO updateVirusTest(Long virusTestId, VirusTestResource virusTestResource);

}
