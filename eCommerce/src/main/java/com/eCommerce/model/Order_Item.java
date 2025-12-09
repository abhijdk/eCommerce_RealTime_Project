package com.eCommerce.model;

import jakarta.persistence.*;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order_Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_Item_Id;

    private Integer order_Id;

    @ManyToOne
    @JoinColumn(name = "product_Id")
    private Product product;

    private Integer quantity;
}
