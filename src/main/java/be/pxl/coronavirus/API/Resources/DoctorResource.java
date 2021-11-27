package be.pxl.coronavirus.API.Resources;

public class DoctorResource {

    private int doctorNumber;
    private String firstName;
    private String lastName;

    public DoctorResource(int doctorNumber, String firstName, String lastName) {
        this.doctorNumber = doctorNumber;
        this.firstName = firstName;
        this.lastName = lastName;
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
