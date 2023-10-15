package com.vti.shopee.modal.request;

import com.vti.shopee.modal.entity.Role;
import lombok.Data;


import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CreateAccountRequest {
    @NotBlank(message = "Ten khong duoc de trong")
    private String username;


    private Role role;

    @NotBlank(message = "password khong duoc de trong")
    @Size(min = 3, max = 6, message = "password phải có từ 3-6 kí tự")
    private String password;


    private Date dateOfBirth;


    private String address;


    private String fullname;

//    @Pattern(regexp = "/(84|0[3|5|7|8|9])+([0-9]{8})\\b/g",
//            message = "So dien thoai khong dung dinh dang")
    private String phoneNumber;


    private String email;


    private String facebook;


    private String information;


//    @Max(value = 3, "So phai be hon 3")
//    @Min(value = 3, "So phai lon hon 3")
//    private int abc;
}


