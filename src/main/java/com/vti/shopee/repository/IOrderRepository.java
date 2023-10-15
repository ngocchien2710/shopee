package com.vti.shopee.repository;

import com.vti.shopee.modal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer >, JpaSpecificationExecutor<Order> {

}
