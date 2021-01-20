package pl.kuziow.vetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kuziow.vetclinic.entity.DoctorEntity;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, Long> {

    DoctorEntity findByDoctorId (String doctorId);
}
