package pl.kuziow.vetclinic.dto;

import pl.kuziow.vetclinic.entity.AppointmentEntity;

import java.io.Serializable;
import java.util.List;

public class DoctorDTO implements Serializable {

    private static final long serialVersionUID = 2082766996481490236L;

    private long id;
    private String doctorId;
    private String name;
    private String lastName;
    private List<AppointmentEntity> appointments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }
}
