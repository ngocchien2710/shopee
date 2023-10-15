package com.vti.shopee.service;

import com.vti.shopee.modal.dto.BaseRequest;
import com.vti.shopee.modal.entity.Account;
import com.vti.shopee.modal.entity.Order;
import com.vti.shopee.modal.entity.Product;
import com.vti.shopee.modal.entity.Status;
import com.vti.shopee.modal.request.SearchOrderRequest;
import com.vti.shopee.repository.IOrderRepository;
import com.vti.shopee.modal.request.CreateOrderRequest;
import com.vti.shopee.modal.request.UpdateOrderRequest;
import com.vti.shopee.repository.specification.OrderSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;


    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    @Override
    public Order getById(int id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<Order> search(SearchOrderRequest request) {
        Specification<Order> condition = OrderSpecification.buildCondition(request);
        PageRequest pageRequest = BaseRequest.buildPageRequest(request);
        return orderRepository.findAll(condition, pageRequest);
    }

    @Override
    public void createOrder(CreateOrderRequest request) {
        Order order = new Order();
        BeanUtils.copyProperties(request, order);
//        Ham ben trai lay gia tri ham ben phai xet gia tri
        Account account = accountService.getById(request.getAccountId());
        Product product = productService.getById(request.getProductId());
        if (product != null && account != null) {
            order.setOderBy(account);
            order.setProductId(product);
            order.setCreateDate(new Date());
            order.setStatus(Status.PENDING);
            orderRepository.save(order);
        }
    }

    @Override
    public Order updateOrder(int id , UpdateOrderRequest request) {

        return null;
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
