package com.vti.shopee.modal.request;

import com.vti.shopee.modal.entity.Status;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateOrderRequest {
    private Date orderDate;

    private int accountId;

    private int productId;

    private int quantity;

    private Status status;
}
