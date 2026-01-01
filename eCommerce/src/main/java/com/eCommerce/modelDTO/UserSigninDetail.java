package com.eCommerce.modelDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSigninDetail {

    Integer user_id;
    String first_Name;
    String last_Name;
    String sigin_Status;
}
