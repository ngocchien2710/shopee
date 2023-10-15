package com.vti.shopee.service;


import com.vti.shopee.modal.entity.Product;

import com.vti.shopee.modal.request.CreateProductRequest;

import com.vti.shopee.modal.request.SearchProductRequest;
import com.vti.shopee.modal.request.UpdateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Page<Product> search(SearchProductRequest request);

    Product getById(int id);

    void createProduct(CreateProductRequest request);

    Product updateProduct(int id, UpdateProductRequest request);

    void deleteProduct(int id);


}
