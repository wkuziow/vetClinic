package pl.kuziow.vetclinic.response;

import java.time.LocalDateTime;

public class AppointmentREST {

    private String appointmentId;
    private CustomerREST customer;
    private LocalDateTime localDateTime;
    private DoctorREST doctor;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public CustomerREST getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerREST customer) {
        this.customer = customer;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public DoctorREST getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorREST doctor) {
        this.doctor = doctor;
    }
}
