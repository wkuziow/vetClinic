package pl.kuziow.vetclinic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "customers")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 538672290251724210L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false, length = 4)
    private String digitID;

    @Column(nullable = false, length = 4)
    private String digitPIN;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 150)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<AppointmentEntity> appointments;

    //password, pet detail, address


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

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }

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
}
