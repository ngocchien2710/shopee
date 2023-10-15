package com.vti.shopee.modal.request;

import com.vti.shopee.modal.entity.Role;
import lombok.Data;

import java.util.Date;

@Data

public class UpdateAccountRequest {
    private Role role;


    private String password;


    private Date dateOfBirth;


    private String address;


    private String fullname;


    private String phoneNumber;


    private String email;


    private String facebook;


    private String information;
}
