package com.vti.shopee.controller;

import com.vti.shopee.config.jwt.JWTTokenUtils;
import com.vti.shopee.exception.AppException;
import com.vti.shopee.exception.ErrorResponseBase;
import com.vti.shopee.modal.dto.LoginDto;
import com.vti.shopee.modal.entity.Account;
import com.vti.shopee.modal.request.LoginRequest;
import com.vti.shopee.repository.IAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
@Validated
public class AuthController {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("/login-v2")
    public LoginDto loginJWT(@RequestBody LoginRequest request) {
        Optional<Account> accountOptional = accountRepository.findByUsername(request.getUsername());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseBase.lOGIN_FAILS_USERNAME);
        }
    // Kiểm tra xem password người dùng truyền vào có đúng hay k
        if (!encoder.matches(request.getPassword(), accountOptional.get().getPassword()) ) {
            //Nếu k khớp password -> bắn ra lỗi
            throw new AppException(ErrorResponseBase.lOGIN_FAILS_PASSWORD);
        }

        //Tạo ra đối tượng trả về
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(accountOptional.get(), loginDto);
        loginDto.setUserAgent(httpServletRequest.getHeader("User-Agent"));// Lấy thông tin trình duyệt đang sử dụng
        String token = jwtTokenUtils.createAccessToken(loginDto); // Tạo token
        loginDto.setToken(token); // set giá trị token vào loginDto để trả về cho người dùng sử dụng
        return loginDto;
    }

    @PostMapping("/login")
    public LoginDto login(Principal principal) { // đối tượng principal có chứa thông tin username
        //Khi qua bước authen sẽ tạo ra đối tượng principal, tại controller sẽ sử dụng lại các giá trị của dữ liệu này
        String username = principal.getName();
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseBase.lOGIN_FAILS);
        }
        LoginDto loginDto =  new LoginDto();
        BeanUtils.copyProperties(accountOptional.get(), loginDto);
        return loginDto;

    }
}
