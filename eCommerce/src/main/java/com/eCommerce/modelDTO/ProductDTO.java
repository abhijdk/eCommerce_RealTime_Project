package com.eCommerce.modelDTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {

    @NotNull(message = "Product Name is Mandatory")
    private String product_Name;

    @NotNull(message = "ImageURL  is Mandatory")
    private String image_Url;

    @NotNull(message = "Description  is Mandatory")
    private String description;

    @NotNull(message = "price  is Mandatory")
    private Double price;

    @NotNull(message = "Category  is Mandatory")
    private String category;
}
