package be.pxl.coronavirus.API;

import be.pxl.coronavirus.API.Resources.VirusTestDTO;
import be.pxl.coronavirus.API.Resources.VirusTestResource;
import be.pxl.coronavirus.domain.Patient;
import be.pxl.coronavirus.domain.VirusTest;
import be.pxl.coronavirus.service.PatientService;
import be.pxl.coronavirus.service.VirusTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("virustest")
@CrossOrigin(origins = "*")
public class VirusTestController {

   private VirusTestService virusTestService;
   private PatientService patientService;

    public VirusTestController(VirusTestService virusTestService, PatientService patientService) {
        this.virusTestService = virusTestService;
        this.patientService = patientService;
    }

    @Secured({"ROLE_DOCTOR"})
    @PostMapping()
    public ResponseEntity<Long> createVirusTestForPatient(@RequestBody VirusTestResource virusTestResource){
        Long result = virusTestService.createVirusTest(virusTestResource);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Secured({"ROLE_DOCTOR", "ROLE_PATIENT"})
    @GetMapping("{patientInsuranceNumber}")
    public ResponseEntity<List<VirusTestDTO>> getAllVirusTestsForPatient(@PathVariable("patientInsuranceNumber") int insuranceNumber){
        Patient patient = patientService.findPatientByInsuranceNumber(insuranceNumber);
        List<VirusTestDTO> result = virusTestService.findVirusTestsForPatient(patient);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @Secured({"ROLE_DOCTOR"})
    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteVirusTest(@PathVariable("id") Long virusTestId){
        virusTestService.deleteVirusTestById(virusTestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_DOCTOR"})
    @PostMapping("{id}")
    public ResponseEntity<VirusTestDTO> updateVirusTest(@PathVariable("id") Long virusTestId, @RequestBody VirusTestResource virusTestResource){
        VirusTestDTO result = virusTestService.updateVirusTest(virusTestId, virusTestResource);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }


}
