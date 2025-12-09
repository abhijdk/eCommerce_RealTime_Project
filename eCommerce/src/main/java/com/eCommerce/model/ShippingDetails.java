package com.eCommerce.model;

import jakarta.persistence.*;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShippingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shipping_Id;

    private String address;

    private String city;

    private String state;

    private String country;

    private Integer zipcode;

//    Shipper
    @ManyToOne
    @JoinColumn(name = "shipper_Id")
    private Shipper shipper;

    @OneToOne
    @JoinColumn(name = "order_Id")
    private Orders orders;
}
