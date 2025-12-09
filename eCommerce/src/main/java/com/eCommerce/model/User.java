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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_Id;

    private String email;

    private String first_Name;

    private String last_Name;

    private String password;

    private String phone_Number;

    private LocalDateTime user_Register_Time;

    @Enumerated(EnumType.STRING)
    private UserAccountStatus user_Account_Status;

    @Enumerated (EnumType.STRING)
    private UserRole user_Role;

//    ADDRESS
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Address> address = new ArrayList<>();

//    ORDER
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

//    PAYMENT
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Payment> payments=new ArrayList<>();

//    REVIEWS
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Review> reviews=new ArrayList<>();

//    CART
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Cart cart;
}
