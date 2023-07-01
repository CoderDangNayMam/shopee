package com.vti.shoppee.entity.dto;

import com.vti.shoppee.config.Annotation.NotAdmin;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.util.Date;
@Data
public class AccountCreateRequestDto {
    @NotBlank(message = "Username không được để trống")
    @NotAdmin
    private String username;

    @NotEmpty(message = "Thiếu password")
    private String password;

    private Date dateOfBirth;

    private String address;

    private String fullName;

    @NotEmpty(message = "Thiếu SĐT")
    @Length(max = 12, message = "SĐT không quá 12 ký tự")
    @Min(value = 10, message = "SĐT chưa đúng")
    private String phoneNumber;

    @NotBlank(message = "Thiếu email")
    @Email(message = "Email chưa đúng định dạng")
    private String email;

    private String information;
}
