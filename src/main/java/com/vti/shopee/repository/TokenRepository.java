package com.vti.shopee.repository;

import com.vti.shopee.modal.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {


    Token findByToken(String token);

    List<Token> findByExpirationIsAfter(Date exDate);
        }
