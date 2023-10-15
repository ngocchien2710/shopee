package com.vti.shopee.modal.request;

import com.vti.shopee.modal.entity.ProductShippingUnit;
import com.vti.shopee.modal.entity.ProductStatus;
import com.vti.shopee.modal.entity.ProductType;
import com.vti.shopee.modal.dto.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductRequest extends BaseRequest {

    private String productName;

    private ProductType productType;

    private ProductShippingUnit shippingUnit;

    private ProductStatus productStatus;

    private Long minPrice;

    private  Long maxPrice;



}
