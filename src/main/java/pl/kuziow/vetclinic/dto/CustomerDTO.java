package pl.kuziow.vetclinic.dto;

import pl.kuziow.vetclinic.entity.AppointmentEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

public class CustomerDTO implements Serializable {


    private static final long serialVersionUID = 5938617630449961800L;
    private long id;
    private String customerId;
    private String digitID;
    private String digitPIN;
    private String name;
    private String lastName;
    private String email;
    private List<AppointmentEntity> appointments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDigitID() {
        return digitID;
    }

    public void setDigitID(String digitID) {
        this.digitID = digitID;
    }

    public String getDigitPIN() {
        return digitPIN;
    }

    public void setDigitPIN(String digitPIN) {
        this.digitPIN = digitPIN;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }
}
