package pl.kuziow.vetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kuziow.vetclinic.entity.AppointmentEntity;
import pl.kuziow.vetclinic.entity.DoctorEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<AppointmentEntity, Long> {

    AppointmentEntity findByAppointmentId(String appoitmentId);

    List<AppointmentEntity> findAllByDoctor (DoctorEntity doctorEntity);

    List<AppointmentEntity> findAllByLocalDateTime (LocalDateTime localDateTime);
}
