package com.vti.shopee.modal.request;

import com.vti.shopee.modal.entity.Status;
import com.vti.shopee.validate.ProductIDExist;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CreateOrderRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    private int accountId;//người order

    @ProductIDExist(message = "đã sửa lại ")
    private int productId;

    private int quantity;//số lượng sản phẩm order

    private Status status;
}
