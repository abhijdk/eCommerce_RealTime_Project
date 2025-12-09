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
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shipper_Id;

    private String name;

    private String phone_Number;

    @OneToMany(mappedBy = "shipper",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ShippingDetails> shipping_Details=new ArrayList<>();
}
