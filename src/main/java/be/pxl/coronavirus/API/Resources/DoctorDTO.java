package be.pxl.coronavirus.API.Resources;

import be.pxl.coronavirus.domain.Doctor;

public class DoctorDTO {

    private final Long id;
    private final int doctorNumber;
    private final String firstName;
    private final String lastName;

    public DoctorDTO (Doctor doctor){
        this.id = doctor.getId();
        this.doctorNumber = doctor.getDoctorNumber();
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
    }

    public Long getId() {
        return id;
    }

    public int getDoctorNumber() {
        return doctorNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
