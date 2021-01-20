package pl.kuziow.vetclinic.service;

import pl.kuziow.vetclinic.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);

    void delete(String appoitmentId);

    List<AppointmentDTO> getAppointmentsByIdAndDate(String doctorId, String date);
}
