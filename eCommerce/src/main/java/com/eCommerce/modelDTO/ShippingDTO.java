package com.eCommerce.modelDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShippingDTO {

    public String address;

    public String city;

    public String state;

    public String zipCode;
}
