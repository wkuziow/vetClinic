package pl.kuziow.vetclinic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "appointment")
public class AppointmentEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String appointmentId;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "customers_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "doctors_id")
    private DoctorEntity doctor;




}
