package com.eCommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {

    @NotNull(message="This field is Mandatory")
    private String productName;

    @NotNull(message="This field is Mandatory")
    private String catagory;


    @NotNull(message="This field is Mandatory")
    private String imageUrl;

    @NotNull(message="This field is Mandatory")
    private String description;

    @NotNull(message="This field is Mandatory")
    private Double price;

}
