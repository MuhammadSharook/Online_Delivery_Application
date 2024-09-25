package com.example.demo.dto.request;

import com.example.demo.Enum.Gender;
import com.example.demo.Enum.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {
    String name;

    String mobileNo;

    Gender gender;

    String role;

    String  mail;

    String password;

    String address;
}
