package com.vti.shoppee.entity.dto;

import com.vti.shoppee.entity.entity.Role;
import lombok.Data;

@Data
public class LoginDtoV2 {
    private int id;
    private String username;
    private Role role;
    private String fullname;

    private String userAgent; // Tên trình duyệt đang được sử dụng
    private String token;
}
