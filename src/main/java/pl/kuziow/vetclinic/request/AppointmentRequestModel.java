package pl.kuziow.vetclinic.request;

import java.time.LocalDateTime;

public class AppointmentRequestModel {

    private CustomerRequestModel customer;
    private LocalDateTime localDateTime;
    private DoctorRequestModel doctor;

    public CustomerRequestModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequestModel customer) {
        this.customer = customer;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public DoctorRequestModel getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorRequestModel doctor) {
        this.doctor = doctor;
    }
}
