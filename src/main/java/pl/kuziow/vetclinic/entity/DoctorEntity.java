package pl.kuziow.vetclinic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "doctors")
public class DoctorEntity implements Serializable {

    private static final long serialVersionUID = -1688079151856460023L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String doctorId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
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
}
