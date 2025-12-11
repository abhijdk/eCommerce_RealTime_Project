package com.eCommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer product_Id;

    private String product_Name;

    private String catagory;

    private String img_Url;

    @NotNull(message="Product Description is Mandatory and can not be null")
    @Size(min=10,max=50)
    private String description;

    private Double price;

    @Column(name = "available")
    private boolean isAvailable=true;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Order_Item> order_items=new ArrayList<>();

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<Review> reviews=new ArrayList<>();
}
