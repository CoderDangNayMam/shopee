package com.vti.shoppee.service.iml;

import com.vti.shoppee.config.exception.AppException;
import com.vti.shoppee.config.exception.ErrorResponseEnum;
import com.vti.shoppee.entity.dto.AccountCreateRequestDto;
import com.vti.shoppee.entity.dto.AccountUpdateDto;
import com.vti.shoppee.entity.entity.Account;
import com.vti.shoppee.entity.entity.Role;
import com.vti.shoppee.repository.AccountRepository;
import com.vti.shoppee.service.IAccountService;
import com.vti.shoppee.service.IEmailSenderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService, UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    protected IEmailSenderService emailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getById(int id){
        Optional<Account> optionalAccount =accountRepository.findById(id);
        if (optionalAccount.isPresent()){
             return optionalAccount.get();
        } else {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
    }



    @Override
    public Account create(AccountCreateRequestDto dto) {
        Account account = new Account();
        BeanUtils.copyProperties(dto,account);

        String pwEncoder = passwordEncoder.encode(dto.getPassword());
        account.setPassword(pwEncoder);
        account.setRole(Role.CUSTOMER);

        // gửi mail, thông báo (active...)
        String text = "Hello "+dto.getUsername()
                + ". Để kích hoạt tài khoản, click: "
                + "<a href=\"http://localhost:8888/api/v1/account/6\" target=\"_blank\">Click me </a>";
        emailSenderService.sendMessageWithHTML(dto.getEmail(), "Kích hoạt tài khoản",text);

        return accountRepository.save(account);
    }

    @Override
    public Account update(AccountUpdateDto dto) {
        Account account = getById(dto.getId());
        if (account!=null){
            BeanUtils.copyProperties(dto,account);
            return accountRepository.save(account);
        } else {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
    }

    @Override
    public void deleteById(int id) {
        accountRepository.deleteById(id);
    }

    /**
     * Dùng Spring security kiểm tra username có tồn tại hay không.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isEmpty()){
            throw new UsernameNotFoundException("Username không tồn tại");
        }
        // nếu account có tồn tại thì tạo ra đối tượng UserDetails từ Acocunt
        Account account = optionalAccount.get();
        /**
         * account.getUsername(): username
         * account.getPassword(): password đã được mã hoá.
         * Collections.emptyList(): list quyền của user
         */
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(account.getRole());
        return new User(username, account.getPassword(),authorityList);
    }
}
