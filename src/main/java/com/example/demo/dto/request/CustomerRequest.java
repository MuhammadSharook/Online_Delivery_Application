package com.example.demo.dto.request;

import com.example.demo.Enum.Gender;
import com.example.demo.Enum.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    String name;

    String email;

    String address;

    String password;

    Role role;

    String mobileNo;

    Gender gender;
}
