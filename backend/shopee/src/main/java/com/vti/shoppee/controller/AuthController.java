package com.vti.shoppee.controller;

import com.vti.shoppee.config.exception.AppException;
import com.vti.shoppee.config.exception.ErrorResponseEnum;
import com.vti.shoppee.entity.dto.LoginDto;
import com.vti.shoppee.entity.dto.LoginDtoV2;
import com.vti.shoppee.entity.dto.LoginRequest;
import com.vti.shoppee.entity.entity.Account;
import com.vti.shoppee.repository.AccountRepository;
import com.vti.shoppee.utils.JWTTokenUtils;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    JWTTokenUtils jwtTokenUtils;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Hàm login: cần set authenticated() cho api này
     * @param principal Đối tượng được sinh ra khi đã xác thực người dùng
     * @return
     */
    @GetMapping("/login-basic-v1")
    public LoginDto loginV1(Principal principal){
        // khi qua bước authen sẽ sinh
        String username = principal.getName();

        // Tìm ra được đối tượng Account từ username.
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isEmpty()){
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
        Account account = optionalAccount.get();
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(account,loginDto);
        return loginDto;
    }

    /**
     * Hàm login: cách này cần permitAll() với api này để xử lý dữ liệu
     * @param username
     * @param password
     * @return
     */
        @GetMapping("/login-basic-v2")
        public LoginDto loginV2(@RequestParam String username, @RequestParam String password){

        // Tìm ra được đối tượng Account từ username.
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isEmpty()){
            throw new AppException(ErrorResponseEnum.USERNAME_NOT_EXISTS);
        }
        Account account = optionalAccount.get();

        // So sánh password
            boolean checkPassword = passwordEncoder.matches(password, account.getPassword());
            if (!checkPassword){
                throw new AppException(ErrorResponseEnum.PASSWORD_INCORRECT);
            }
            LoginDto loginDto = new LoginDto();
            BeanUtils.copyProperties(account,loginDto);
            return loginDto;
    }

    /**
     * Hàm login JWT: cách này cần permitAll() với api này để xử lý dữ liệu
     * @param request: đối tượng gồm username và password
     * @return
     */
    @PostMapping("/login-jwt")
    public LoginDtoV2 loginJWT(@RequestBody LoginRequest request){

        // Tìm ra được đối tượng Account từ username.
        Optional<Account> optionalAccount = accountRepository.findByUsername(request.getUsername());
        if (optionalAccount.isEmpty()){
            throw new AppException(ErrorResponseEnum.USERNAME_NOT_EXISTS);
        }
        Account account = optionalAccount.get();

        // So sánh password
        boolean checkPassword = passwordEncoder.matches(request.getPassword(), account.getPassword());
        if (!checkPassword){
            throw new AppException(ErrorResponseEnum.PASSWORD_INCORRECT);
        }

        // Login đã thành công -> tạo ra mã token để gán vào LoginDtoV2

        LoginDtoV2 loginDtoV2 = new LoginDtoV2();
        BeanUtils.copyProperties(account,loginDtoV2);
        loginDtoV2.setUserAgent(httpServletRequest.getHeader("User-Agent"));

        String token = jwtTokenUtils.createAccessToken(loginDtoV2);
        loginDtoV2.setToken(token);

        return loginDtoV2;
    }
}
