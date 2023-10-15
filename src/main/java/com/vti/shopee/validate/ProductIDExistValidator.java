package com.vti.shopee.validate;

import com.vti.shopee.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIDExistValidator implements ConstraintValidator<ProductIDExist, Integer> {
    @Autowired
    IProductRepository productRepository;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.existsById(integer);
    }
}
