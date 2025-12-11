package com.eCommerce.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotNull(message="This field is Mandatory")
    private String email;

    @NotNull(message="This field is Mandatory")
    private String firstName;

    @NotNull(message="This field is Mandatory")
    private String lastName;

    @NotNull(message="This field is Mandatory")
    private String password;

    @NotNull(message="This field is Mandatory")
    private String phoneNumber;
}
