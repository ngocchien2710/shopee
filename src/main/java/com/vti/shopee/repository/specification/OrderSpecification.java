package com.vti.shopee.repository.specification;

import com.vti.shopee.modal.entity.Account;
import com.vti.shopee.modal.entity.Order;

import com.vti.shopee.modal.request.SearchOrderRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class OrderSpecification {
    public static Specification<Order> buildCondition(SearchOrderRequest request) {
        return Specification.where(byOderBy(request.getOrderBy()));
    }
    private static Specification<Order> byOderBy(int accountId) {
        if (accountId >0) {
            return (root, query, cri) -> {
            Join<Order, Account> joiner = root.join("orderBy");
            return cri.equal(joiner.get("id"), accountId);
            };
        }
        return null;
        }
    }


