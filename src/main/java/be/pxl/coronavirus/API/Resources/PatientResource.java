package be.pxl.coronavirus.API.Resources;

public class PatientResource {

    private int InsuranceNumber;
    private String firstName;
    private String lastName;

    public PatientResource(int insuranceNumber, String firstName, String lastName) {
        InsuranceNumber = insuranceNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getInsuranceNumber() {
        return InsuranceNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
