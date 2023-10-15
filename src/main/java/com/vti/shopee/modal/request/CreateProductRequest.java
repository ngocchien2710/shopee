package com.vti.shopee.modal.request;

import com.vti.shopee.modal.entity.ProductShippingUnit;
import com.vti.shopee.modal.entity.ProductStatus;
import com.vti.shopee.modal.entity.ProductType;

import javax.validation.constraints.NotBlank;

public class CreateProductRequest {
    @NotBlank(message = "Khong duoc bo trong")
    private String name;

    @NotBlank(message ="{product.test}")
    private String image;


    private int price;


    private ProductStatus status;


    private ProductShippingUnit shippingUnit;


    private ProductType type;
}
