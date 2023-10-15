package com.vti.shopee.modal.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="`Account`")
@Data
public class Account {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name="`role`")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name ="`password`", length = 50, nullable = false)
    private String password;

    @Column(name ="date_of_birth ")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateOfBirth;

    @Column(name ="address", length = 255)
    private String address;

    @Column(name ="fullname", length = 100)
    private String fullname;

    @Column(name ="phone_number", length = 12)
    private String phoneNumber;

    @Column(name ="email", length = 50)
    private String email;

    @Column(name= "facebook", length = 50)
    private String facebook;

    @Column(name ="information", length = 500)
    private String information;
}
