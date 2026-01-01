package com.eCommerce.modelDTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {

    @NotNull(message = "UserName is Mandatory")
    private String email;  //UserName will be email

    @NotNull(message = "FirstName is Mandatory")
    private String first_Name;

    @NotNull(message = "LastName is Mandatory")
    private String last_Name;

    @NotNull(message = "Password is Mandatory")
    private String password;

    @NotNull(message = "PhoneNumber is Mandatory")
    private String phone_Number;
}
