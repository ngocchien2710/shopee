package com.vti.shopee.service;

import com.vti.shopee.exception.AppException;
import com.vti.shopee.exception.ErrorResponseBase;
import com.vti.shopee.modal.entity.Account;
import com.vti.shopee.repository.IAccountRepository;
import com.vti.shopee.modal.request.CreateAccountRequest;
import com.vti.shopee.modal.request.UpdateAccountRequest;
import com.vti.shopee.security.UserDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService, UserDetailsService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<Account> getAllAccount() {

        return accountRepository.findAll();
    }

    @Override
    public Account getById(int id) {
        Optional<Account> optional = accountRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_FOUND);
        }
        try {
            return optional.get();
        } catch (Exception ex) {
            //dù có lỗi gì xảy ra thì throw ra 1 đối tượng app exception
            throw new AppException(ex);
        }

    }



    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createAccount(CreateAccountRequest request) {
        Account account = new Account();
        String encodePassword = encoder.encode(request.getPassword());// lấy ra dãy mã hóa password
        BeanUtils.copyProperties(request, account);
        account.setPassword(encodePassword);// set password ở db chính là dãy mã hóa
        //Kiểm tra username đã tồn tại chưa
        Optional<Account> accountCheck = accountRepository.findByUsername(request.getUsername());
        if (accountCheck.isPresent()) {
            //username đã tồn tại thì sẽ bắn lỗi
            throw new AppException(ErrorResponseBase.USERNAME_EXISTED);
        }

            accountRepository.save(account);

    }

    @Override
    public Account updateAccount(int id, UpdateAccountRequest request) {
        Account accountDb = getById(id);

        if (accountDb != null) {
            BeanUtils.copyProperties(request, accountDb);
            accountDb.setId(id);
            return accountRepository.save(accountDb);

        }
        return null;
    }

    @Override
    public void deleteAccount(int id) {

        accountRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
        Optional<Account> optional = accountRepository.findByUsername(username);
        if (optional.isPresent()) {
            Account account = optional.get();
            //lấy ra giá trị để phân quyền
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(account.getRole());
            return new User(account.getUsername(), account.getPassword(), authorities);
        }else {
            throw new UsernameNotFoundException(username);
        }

    }
}
