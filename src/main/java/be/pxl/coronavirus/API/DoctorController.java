package be.pxl.coronavirus.API;

import be.pxl.coronavirus.API.Resources.DoctorResource;
import be.pxl.coronavirus.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("doctorCreation")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @Secured("ROLE_DOCTOR")
    @PostMapping
    public ResponseEntity<Long> CreateDoctor(@RequestBody @Valid DoctorResource doctorResource){
        Long result = doctorService.createDoctor(doctorResource);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


}
