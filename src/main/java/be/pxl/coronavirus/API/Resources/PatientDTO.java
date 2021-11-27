package be.pxl.coronavirus.API.Resources;

import be.pxl.coronavirus.domain.Patient;

public class PatientDTO {

    private final Long id;
    private final int insuranceNumber;
    private final String firstName;
    private final String lastName;

    public PatientDTO(Patient patient) {
        this.id = patient.getId();
        this.insuranceNumber = patient.getInsuranceNumber();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
    }

    public Long getId() {
        return id;
    }

    public int getInsuranceNumber() {
        return insuranceNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
