package com.eCommerce.modelDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {

    private Integer order_Id;

    private String order_Date;

    private Integer order_Amount;


    private String  status;

    private String payment_Status;

}
