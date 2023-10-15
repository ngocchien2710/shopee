package com.vti.shopee.service;

import com.vti.shopee.modal.entity.Account;
import com.vti.shopee.modal.request.CreateAccountRequest;
import com.vti.shopee.modal.request.UpdateAccountRequest;

import java.util.List;

public interface IAccountService {
    List<Account> getAllAccount();

    Account getById(int id);

    void createAccount(CreateAccountRequest request);

    Account updateAccount(int id, UpdateAccountRequest request);

    void deleteAccount(int id);


}
