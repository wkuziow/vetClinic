package pl.kuziow.vetclinic.dto;

import pl.kuziow.vetclinic.entity.CustomerEntity;
import pl.kuziow.vetclinic.entity.DoctorEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AppointmentDTO implements Serializable {

    private static final long serialVersionUID = -5372110032482593570L;

    private long id;
    private String appointmentId;
    private LocalDateTime localDateTime;
    private CustomerDTO customer;
    private DoctorDTO doctor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }
}
