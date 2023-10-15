package com.vti.shopee.modal.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="`name`", length = 255, unique = true)
    private String name;

    @Column(name ="image", length = 255)
    private String image;

    @Column(name="price")
    private int price;

    @Column(name="`status`")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name="shipping_unit")
    @Enumerated(EnumType.STRING)
    private ProductShippingUnit shippingUnit;

    @Column(name="`type`")
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Column(name="create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;
}
