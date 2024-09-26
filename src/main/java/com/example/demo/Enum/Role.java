package com.example.demo.Enum;

import lombok.ToString;

public enum Role {
    CUSTOMER("customer"),
    ADMIN("admin"),
    VENDOR("vendor"),
    DELIVERYPARTNER("deliverypartner");

    private String roleName;
    Role(String roleName) {
        this.roleName = roleName;
    }


}
