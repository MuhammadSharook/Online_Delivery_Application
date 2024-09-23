package com.example.demo.dto.response;

import com.example.demo.Enum.Gender;
import com.example.demo.Enum.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    String name;

    String mobileNo;

    String address;

    CartResponse cart;

    String password;

    Role role;

    Gender gender;


}
