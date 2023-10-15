package com.vti.shopee.modal.request;

import com.vti.shopee.modal.dto.BaseRequest;
import com.vti.shopee.modal.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class SearchOrderRequest extends BaseRequest {
    private int orderBy;

    private Status status;

}
