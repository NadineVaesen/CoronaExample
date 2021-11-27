package be.pxl.coronavirus.repository;

import be.pxl.coronavirus.domain.Patient;
import be.pxl.coronavirus.domain.VirusTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirusTestRepository extends JpaRepository<VirusTest, Long> {

    List<VirusTest> findAllByPatient(Patient patient);
    void deleteVirusTestById(Long id);


}