package com.vti.shopee.controller;


import com.vti.shopee.modal.entity.Product;
import com.vti.shopee.modal.request.CreateProductRequest;
import com.vti.shopee.modal.request.SearchProductRequest;
import com.vti.shopee.modal.request.UpdateProductRequest;
import com.vti.shopee.service.ProductService;
import com.vti.shopee.validate.ProductIDExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin("*")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchProductRequest request) {

        return productService.search(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Product getById(@PathVariable @ProductIDExist int id) {
        return productService.getById(id);
    }

    @PostMapping("/create")
    public void createProduct(@RequestBody @Valid  CreateProductRequest request){
        productService.createProduct(request);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody @Valid UpdateProductRequest request, @PathVariable int id){
        productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
