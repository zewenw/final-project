package com.finalproject.demomodule.entity.unidirection;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "uniOrderItem")
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id",  referencedColumnName = "id")
    private Product product;
}
