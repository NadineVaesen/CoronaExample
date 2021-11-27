package be.pxl.coronavirus.repository;

import be.pxl.coronavirus.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findDoctorByDoctorNumber(int doctorNumber);
}
