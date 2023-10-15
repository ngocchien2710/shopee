package com.vti.shopee.controller;

import com.vti.shopee.modal.entity.Order;
import com.vti.shopee.modal.request.CreateOrderRequest;
import com.vti.shopee.modal.request.SearchOrderRequest;
import com.vti.shopee.modal.request.UpdateOrderRequest;
import com.vti.shopee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin("*")
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;



    @GetMapping("/get-all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(int id) {
        Order order = orderService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderRequest request) {
            orderService.createOrder(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public  void updateOrder(@PathVariable int id, UpdateOrderRequest request) {
        orderService.updateOrder(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }

    @PostMapping("/search")
    public Page<Order> search(@RequestBody SearchOrderRequest request){
        return orderService.search(request);
    }


}

