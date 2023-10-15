package com.vti.shopee.service;

import com.vti.shopee.modal.entity.Order;
import com.vti.shopee.modal.request.CreateOrderRequest;
import com.vti.shopee.modal.request.SearchOrderRequest;
import com.vti.shopee.modal.request.UpdateOrderRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();

    Order getById(int id);

    Page<Order> search(SearchOrderRequest request);

    void createOrder(CreateOrderRequest request);

    Order updateOrder(int id, UpdateOrderRequest request);

    void deleteOrder(int id);



}
