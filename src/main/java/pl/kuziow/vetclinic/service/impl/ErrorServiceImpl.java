package pl.kuziow.vetclinic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kuziow.vetclinic.dto.AppointmentDTO;
import pl.kuziow.vetclinic.entity.AppointmentEntity;
import pl.kuziow.vetclinic.entity.CustomerEntity;
import pl.kuziow.vetclinic.entity.DoctorEntity;
import pl.kuziow.vetclinic.exceptions.AppointmentServiceException;
import pl.kuziow.vetclinic.repositories.AppointmentRepository;
import pl.kuziow.vetclinic.repositories.ErrorMessages;
import pl.kuziow.vetclinic.request.AppointmentRequestModel;
import pl.kuziow.vetclinic.service.ErrorService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void checkRequiredFields(AppointmentRequestModel appointmentDTO) {
        if (appointmentDTO.getCustomer().getEmail() == null ||
                appointmentDTO.getCustomer().getEmail().isEmpty() ||

                appointmentDTO.getCustomer().getDigitID() == null ||
                appointmentDTO.getCustomer().getDigitID().isEmpty() ||

                appointmentDTO.getCustomer().getDigitPIN() == null ||
                appointmentDTO.getCustomer().getDigitPIN().isEmpty() ||

                appointmentDTO.getLocalDateTime() == null ||

                appointmentDTO.getDoctor().getDoctorId() == null ||
                appointmentDTO.getDoctor().getDoctorId().isEmpty())
            throw new AppointmentServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    @Override
    public void verifyIDAndPIN(CustomerEntity customerEntity, AppointmentDTO appointmentDTO) {
        if (!customerEntity.getDigitID().equals(appointmentDTO.getCustomer().getDigitID()) ||
                !customerEntity.getDigitPIN().equals(appointmentDTO.getCustomer().getDigitPIN()))
            throw new AppointmentServiceException(ErrorMessages.AUTHENTICATION_FAILED.getErrorMessage());

    }

    @Override
    public void customerExists(CustomerEntity customerEntity) {
        if (customerEntity == null)
            throw new AppointmentServiceException((ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }

    @Override
    public void doctorExists(DoctorEntity doctorEntity) {
        if (doctorEntity == null)
            throw new AppointmentServiceException((ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

    }

    @Override
    public void appointmentExists(AppointmentEntity appointmentEntity) {
        if (appointmentEntity == null)
            throw new AppointmentServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

    }

    @Override
    public void appointmentExistsBeforeSaving(AppointmentEntity appointmentEntity) {
        LocalDateTime localDateTime = appointmentEntity.getLocalDateTime();
        List<AppointmentEntity> foundAppointments = appointmentRepository.findAllByLocalDateTime(localDateTime);
        CustomerEntity customerEntity = appointmentEntity.getCustomer();
        DoctorEntity doctorEntity = appointmentEntity.getDoctor();
        List<AppointmentEntity> filteredList = foundAppointments.stream()
                .filter(c -> c.getCustomer().equals(customerEntity))
                .filter(d -> d.getDoctor().equals(doctorEntity))
                .collect(Collectors.toList());
        if (filteredList.size() > 0)
            throw new AppointmentServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
    }
}
