package pl.kuziow.vetclinic.service;

import pl.kuziow.vetclinic.dto.AppointmentDTO;
import pl.kuziow.vetclinic.entity.AppointmentEntity;
import pl.kuziow.vetclinic.entity.CustomerEntity;
import pl.kuziow.vetclinic.entity.DoctorEntity;
import pl.kuziow.vetclinic.request.AppointmentRequestModel;

public interface ErrorService {
    void checkRequiredFields(AppointmentRequestModel appointmentDTO);

    void verifyIDAndPIN(CustomerEntity customerEntity, AppointmentDTO appointmentDTO);

    void customerExists(CustomerEntity customerEntity);

    void doctorExists(DoctorEntity doctorEntity);

    void appointmentExists(AppointmentEntity appointmentEntity);

    void appointmentExistsBeforeSaving(AppointmentEntity appointmentEntity);
}
