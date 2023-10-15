package com.vti.shopee.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BaseRequest {
    @Min(value = 0, message = "Size phải lớn hơn 0")
    protected int pageSize;
    @Min(value = 0, message = "Số trang phải lớn hơn 0")
    protected int pageNumber;
    protected String sortBy;
    protected String sortType;

    public static PageRequest buildPageRequest(BaseRequest baseRequest){
            PageRequest pageRequest = null;
            if (baseRequest.getSortType().equals("DESC")) {
                pageRequest = PageRequest.of(baseRequest.getPageNumber(), baseRequest.getPageSize(), Sort.by(baseRequest.getSortBy()).descending());
            } else {
                pageRequest = PageRequest.of(baseRequest.getPageNumber(), baseRequest.getPageSize(), Sort.by(baseRequest.getSortBy()).ascending());
            }
            return pageRequest;
        }
    }



