package com.vti.shopee.repository.specification;

import com.vti.shopee.modal.entity.Product;
import com.vti.shopee.modal.entity.ProductShippingUnit;
import com.vti.shopee.modal.entity.ProductStatus;
import com.vti.shopee.modal.entity.ProductType;
import com.vti.shopee.modal.request.SearchProductRequest;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> buildCondition(SearchProductRequest request){
        return Specification.where(byProductName(request.getProductName()))
                .and(byProductType(request.getProductType()))
                .and(byProductShippingUnit(request.getShippingUnit()))
                .and(byProductStatus(request.getProductStatus()))
                .and(byPriceMax(request.getMaxPrice()))
                .and(byPriceMin(request.getMinPrice()));
    }
    private static Specification<Product> byProductName(String productName) {
        if (productName != null) {
            return (root, query, cri) -> {
                return cri.like(root.get("name"), "%" + productName + "%");
            };

        }
        return null;
    }


    private static Specification<Product> byProductType(ProductType productType) {
        if (productType != null) {
            return (root, query, cri) -> {
                return cri.equal(root.get("type"), productType);
            };
        }

        return null;
    }

    private static Specification<Product> byProductStatus(ProductStatus productStatus) {
        if (productStatus != null) {
            return (root, query, cri) -> {
                return cri.equal(root.get("status"), productStatus);
            };
        }
        return null;
    }

    private static Specification<Product> byProductShippingUnit(ProductShippingUnit productShippingUnit) {
        if (productShippingUnit != null) {
            return (root, query, cri) -> {
                return cri.equal(root.get("shippingUnit"), productShippingUnit);
            };
        }
        return null;
    }

    private static Specification<Product> byPriceMax(Long maxPrice) {
        if (maxPrice != null) {
            return (root, query, cri) -> {
                return cri.greaterThanOrEqualTo(root.get("price"), maxPrice);
            };
        }
        return null;
    }

    private static Specification<Product> byPriceMin(Long minPrice) {
        if (minPrice != null) {
            return (root, query, cri) -> {
                return cri.greaterThanOrEqualTo(root.get("price"), minPrice);
            };
        }
        return null;
    }
}


