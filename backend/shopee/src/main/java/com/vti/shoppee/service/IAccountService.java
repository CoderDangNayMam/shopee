package com.vti.shoppee.service;

import com.vti.shoppee.entity.dto.AccountCreateRequestDto;
import com.vti.shoppee.entity.dto.AccountUpdateDto;
import com.vti.shoppee.entity.dto.BaseRequest;
import com.vti.shoppee.entity.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {
    List<Account> getAll();

    Account getById(int id);

    Account create(AccountCreateRequestDto dto);

    Account update(AccountUpdateDto dto);

    void deleteById(int id);

}
