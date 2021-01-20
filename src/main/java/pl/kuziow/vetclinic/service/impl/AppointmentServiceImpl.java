package pl.kuziow.vetclinic.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kuziow.vetclinic.dto.AppointmentDTO;
import pl.kuziow.vetclinic.dto.CustomerDTO;
import pl.kuziow.vetclinic.entity.AppointmentEntity;
import pl.kuziow.vetclinic.entity.CustomerEntity;
import pl.kuziow.vetclinic.entity.DoctorEntity;
import pl.kuziow.vetclinic.repositories.AppointmentRepository;
import pl.kuziow.vetclinic.repositories.CustomerRepository;
import pl.kuziow.vetclinic.repositories.DoctorRepository;
import pl.kuziow.vetclinic.service.AppointmentService;
import pl.kuziow.vetclinic.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    Utils utils;


    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {

        //TODO weryfikacja czy pola nie są puste
        String customerEmail = appointmentDTO.getCustomer().getEmail();
        CustomerEntity customerEntity = customerRepository.findByEmail(customerEmail);
        //TODO weryfikacja ID i PIN
        //TODO weryfikacja czy customer istnieje
        String doctorId = appointmentDTO.getDoctor().getDoctorId();
        DoctorEntity doctorEntity = doctorRepository.findByDoctorId(doctorId);

        //TODO weryfikacja czy doktor istnieje

        ModelMapper modelMapper = new ModelMapper();

        AppointmentEntity appointmentEntity = modelMapper.map(appointmentDTO, AppointmentEntity.class);
        appointmentEntity.setCustomer(customerEntity);
        appointmentEntity.setDoctor(doctorEntity);

        String publicAppointmentId = utils.generateAppointmentId(30);

        appointmentEntity.setAppointmentId(publicAppointmentId);

        AppointmentEntity storedAppointemnt = appointmentRepository.save(appointmentEntity);

        AppointmentDTO returnValue = modelMapper.map(storedAppointemnt, AppointmentDTO.class);

        return returnValue;
    }

    @Override
    public void delete(String appoitmentId) {
        AppointmentEntity appointmentEntity = appointmentRepository.findByAppointmentId(appoitmentId);
        //TODO
        //if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        appointmentRepository.delete(appointmentEntity);

    }

    @Override
    public List<AppointmentDTO> getAppointmentsByIdAndDate(String doctorId, String date) {
        List<AppointmentDTO> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        DoctorEntity doctorEntity = doctorRepository.findByDoctorId(doctorId);
        if (doctorEntity == null) {
            return returnValue;
        }
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findAllByDoctor(doctorEntity);
        LocalDate localDate = LocalDate.parse(date);
        appointmentEntities.stream()
                .filter(d -> d.getLocalDateTime().toLocalDate().equals(localDate))
                .collect(Collectors.toList());
        for (AppointmentEntity appointmentEntity : appointmentEntities) {
            returnValue.add(modelMapper.map(appointmentEntity, AppointmentDTO.class));
        }

        return returnValue;
    }
}
