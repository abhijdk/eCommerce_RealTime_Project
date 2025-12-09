package com.eCommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_Id;

    private LocalDateTime order_Date;

    private Integer total_Amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

//    Order Item
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Order_Item> order_items=new ArrayList<>();

//    Payment
    @OneToOne
    @JoinColumn(name = "payment_Id")
    private Payment payment;

//    Shipment
    @OneToOne
    @JoinColumn(name = "shipping_Id")
    private ShippingDetails shipment;

//    User
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
