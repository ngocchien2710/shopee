package com.vti.shopee.modal.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @JoinColumn(name ="oder_by")
    @ManyToOne
    private Account oderBy;

    @JoinColumn(name="product_id")
    @ManyToOne
    private Product productId;

    @Column(name="quantity")
    private int quantity;

    @Column(name="`status`")
    private Status status;
}
