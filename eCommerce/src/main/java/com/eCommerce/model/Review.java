package com.eCommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Review {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer review_Id;

    @Size(max=5,message = "Please provide Rating..Can not be Null")
    private Integer rating;

    @NotNull(message="Please Provide comment, cant be Null")
    private String comment;

    private LocalDateTime created_At;

    @ManyToOne
    @JoinColumn(name = "product_Id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
}
