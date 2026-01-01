package com.eCommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {

    @NotNull(message="This field is Mandatory")
    private Integer orderId;

    @NotNull(message="This field is Mandatory")
    private String orderDate;

    @NotNull(message="This field is Mandatory")
    private Integer orderAmount;

    @NotNull(message="This field is Mandatory")
    private String orderStatus;

    @NotNull(message="This field is Mandatory")
    private String paymentStatus;
}
