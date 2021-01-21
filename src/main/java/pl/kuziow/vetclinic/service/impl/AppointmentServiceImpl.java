package pl.kuziow.vetclinic.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kuziow.vetclinic.dto.AppointmentDTO;
import pl.kuziow.vetclinic.entity.AppointmentEntity;
import pl.kuziow.vetclinic.entity.CustomerEntity;
import pl.kuziow.vetclinic.entity.DoctorEntity;
import pl.kuziow.vetclinic.repositories.AppointmentRepository;
import pl.kuziow.vetclinic.repositories.CustomerRepository;
import pl.kuziow.vetclinic.repositories.DoctorRepository;
import pl.kuziow.vetclinic.service.AppointmentService;
import pl.kuziow.vetclinic.service.ErrorService;
import pl.kuziow.vetclinic.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
    ErrorService errorService;

    @Autowired
    Utils utils;


    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws Exception{

        String customerEmail = appointmentDTO.getCustomer().getEmail();
        CustomerEntity customerEntity = customerRepository.findByEmail(customerEmail);

        errorService.customerExists(customerEntity);

        errorService.verifyIDAndPIN(customerEntity, appointmentDTO);

        String doctorId = appointmentDTO.getDoctor().getDoctorId();
        DoctorEntity doctorEntity = doctorRepository.findByDoctorId(doctorId);

        errorService.doctorExists(doctorEntity);

        ModelMapper modelMapper = new ModelMapper();

        AppointmentEntity appointmentEntity = modelMapper.map(appointmentDTO, AppointmentEntity.class);
        appointmentEntity.setCustomer(customerEntity);
        appointmentEntity.setDoctor(doctorEntity);

        String publicAppointmentId = utils.generateAppointmentId(30);

        appointmentEntity.setAppointmentId(publicAppointmentId);

        errorService.appointmentExistsBeforeSaving(appointmentEntity);

        AppointmentEntity storedAppointemnt = appointmentRepository.save(appointmentEntity);

        AppointmentDTO returnValue = modelMapper.map(storedAppointemnt, AppointmentDTO.class);

        return returnValue;
    }

    @Override
    public void delete(String appoitmentId) {
        AppointmentEntity appointmentEntity = appointmentRepository.findByAppointmentId(appoitmentId);

        errorService.appointmentExists(appointmentEntity);
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
        List<AppointmentEntity> finalAppointmentEntities = appointmentEntities.stream()
                .filter(d -> d.getLocalDateTime().toLocalDate().equals(localDate))
                .sorted(Comparator.comparing(AppointmentEntity::getLocalDateTime))
                .collect(Collectors.toList());
        for (AppointmentEntity appointmentEntity : finalAppointmentEntities) {
            returnValue.add(modelMapper.map(appointmentEntity, AppointmentDTO.class));
        }
        return returnValue;
    }
}
