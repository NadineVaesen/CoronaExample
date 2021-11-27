package be.pxl.coronavirus.API.Resources;

public class VirusTestResource {

    private final int insuranceNumber;
    private final boolean isPositive;

    public VirusTestResource(int insuranceNumber, boolean isPositive) {
        this.insuranceNumber = insuranceNumber;
        this.isPositive = isPositive;
    }

    public int getInsuranceNumber() {
        return insuranceNumber;
    }

    public boolean isPositive() {
        return isPositive;
    }
}
