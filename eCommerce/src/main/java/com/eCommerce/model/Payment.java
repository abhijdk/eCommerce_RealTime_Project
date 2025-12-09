package com.eCommerce.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer payment_Id;

    private LocalDateTime payment_Date;

    private Double payment_Amount;

    @Enumerated(EnumType.STRING)
    private Payment_Status payment_status;

    @Enumerated(EnumType.STRING)
    private Payment_Method payment_method;

    @OneToOne
    @JoinColumn(name = "order_Id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
