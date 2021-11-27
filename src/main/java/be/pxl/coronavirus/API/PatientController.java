package be.pxl.coronavirus.API;

import be.pxl.coronavirus.API.Resources.PatientDTO;
import be.pxl.coronavirus.API.Resources.PatientResource;
import be.pxl.coronavirus.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("doctor")

public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Secured("ROLE_DOCTOR")
    @GetMapping("{id}/patients")
    public ResponseEntity<List<PatientDTO>> getPatients(@PathVariable("id") int doctorNumber){
        List<PatientDTO> result = patientService.getPatientsByDoctor(doctorNumber);
        System.out.println(result);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("{id}")
    @Secured({"ROLE_DOCTOR","ROLE_PATIENT"})
    public ResponseEntity<Long> createPatient(@PathVariable("id") int doctorNumber, @RequestBody @Valid PatientResource patientResource){
        Long result = patientService.createPatient(patientResource, doctorNumber);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Secured("ROLE_DOCTOR")
    @GetMapping("{doctorNumber}/positive")
    public ResponseEntity<List<PatientDTO>> getPatientsByDoctorAndPositive(@PathVariable("doctorNumber") int doctorNumber){
        System.out.println("in getpatientsbydoctor and positive");
        List<PatientDTO> patientDTOList = patientService.getPatientsByDoctorAndPositive(doctorNumber);
        if (patientDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(patientDTOList, HttpStatus.OK);
        }

    }



}
