package com.eCommerce.model;

import jakarta.persistence.*;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart_Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cart_Item_Id;

    private  Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_Id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_Id")
    private Product product;
}
