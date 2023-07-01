package com.vti.shoppee.entity.dto;

import com.vti.shoppee.entity.entity.Role;
import lombok.Data;

@Data
public class LoginDto {
    private int id;
    private String username;
    private Role role;
    private String fullname;

}
