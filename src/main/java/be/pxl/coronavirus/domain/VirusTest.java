package be.pxl.coronavirus.domain;

import javax.persistence.*;

@Entity
@Table(name = "VIRUSTESTS")
public class VirusTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private boolean isPositive;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public VirusTest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
