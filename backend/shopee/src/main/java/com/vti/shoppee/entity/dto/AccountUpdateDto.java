package com.vti.shoppee.entity.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AccountUpdateDto {
    private int id;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String information;
}
