package com.eCommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer address_Id;

    @NotNull(message = "FlatNo is Mandatory")
    @NotBlank(message = "FlatNo is Mandatory")
    private Integer flatNo;

    @NotNull(message = "Street is Mandatory")
    @NotBlank(message = "Street is Mandatory")
    private String street;

    @Size(max=20)
    @NotNull(message = "City is Mandatory")
    @NotBlank(message = "City is Mandatory")
    private String city;

    @Size(max=20)
    @NotNull(message = "State is Mandatory")
    @NotBlank(message = "State is Mandatory")
    private String state;

    @Size(max=6)
    @NotNull(message = "Pincode is Mandatory")
    @NotBlank(message = "Pincode is Mandatory")
    private Integer zip_Code;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
}
