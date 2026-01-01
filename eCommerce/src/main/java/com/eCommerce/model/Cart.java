package com.eCommerce.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer cart_Id;

    private Integer total_Amount;

    @OneToOne
    @JoinColumn(name = "user_Id")
    private User user;

//    Cart Item

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<Cart_Item> cart_items=new ArrayList<Cart_Item>();
}
