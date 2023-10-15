package com.vti.shopee.service;


import com.vti.shopee.exception.AppException;
import com.vti.shopee.exception.ErrorResponseBase;
import com.vti.shopee.modal.dto.BaseRequest;
import com.vti.shopee.modal.entity.Product;
import com.vti.shopee.repository.IProductRepository;
import com.vti.shopee.modal.request.CreateProductRequest;
import com.vti.shopee.modal.request.SearchProductRequest;
import com.vti.shopee.modal.request.UpdateProductRequest;
import com.vti.shopee.repository.specification.ProductSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> search(SearchProductRequest request) {
        long minPrice = request.getMinPrice();
        long maxPrice = request.getMaxPrice();
        if (minPrice >= maxPrice) {
            throw new AppException(ErrorResponseBase.MIN_MAX_INVALID);
        }
        Specification<Product> condition = ProductSpecification.buildCondition(request);
        PageRequest pageRequest = BaseRequest.buildPageRequest(request);
        return productRepository.findAll(condition, pageRequest);
    }

    @Override
    public Product getById(int id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void createProduct(CreateProductRequest request) {
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, UpdateProductRequest request) {
        Product productDb = getById(id);
        if (productDb != null) {
            BeanUtils.copyProperties(request, productDb);
            productDb.setId(id);
            return productRepository.save(productDb);
        }
        return null;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }




}
