package com.vti.shopee.controller;



import com.vti.shopee.modal.entity.Account;
import com.vti.shopee.modal.request.CreateAccountRequest;
import com.vti.shopee.modal.request.UpdateAccountRequest;
import com.vti.shopee.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
@CrossOrigin("*")
@Validated
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/get-all")
    public List<Account> getAllAccounts() {

        return accountService.getAllAccount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable  int id) {
        Account account = accountService.getById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(account);

    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody @Valid  CreateAccountRequest request){
            accountService.createAccount(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/id")
    public void updateAccount(@RequestBody UpdateAccountRequest request, @PathVariable int id){
        accountService.updateAccount(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
    }

}




