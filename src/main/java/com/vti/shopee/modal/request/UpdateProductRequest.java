package com.vti.shopee.modal.request;

import com.vti.shopee.modal.entity.ProductShippingUnit;
import com.vti.shopee.modal.entity.ProductStatus;
import com.vti.shopee.modal.entity.ProductType;
import lombok.Data;

@Data
public class UpdateProductRequest {

    private String name;


    private String image;


    private int price;


    private ProductStatus status;


    private ProductShippingUnit shippingUnit;


    private ProductType type;
}
