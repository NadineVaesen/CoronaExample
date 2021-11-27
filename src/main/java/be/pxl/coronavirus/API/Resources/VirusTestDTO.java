package be.pxl.coronavirus.API.Resources;

import be.pxl.coronavirus.domain.Patient;
import be.pxl.coronavirus.domain.VirusTest;

public class VirusTestDTO {

    private final Long id;
    private final boolean isPositive;


    public VirusTestDTO(VirusTest virusTest) {
        this.id = virusTest.getId();
        this.isPositive = virusTest.isPositive();

    }

    public Long getId() {
        return id;
    }

    public boolean isPositive() {
        return isPositive;
    }


}
