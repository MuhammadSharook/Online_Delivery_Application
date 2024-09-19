package com.example.demo.transformer;

import com.example.demo.dto.request.AdminRequest;
import com.example.demo.dto.response.AdminResponse;
import com.example.demo.model.Admin;

public class AdminTransformer {

    public static Admin fromAdminRequestToAdmin(AdminRequest adminRequest){
        return Admin.builder()
                .name(adminRequest.getName())
                .mobileNo(adminRequest.getMobileNo())
                .address(adminRequest.getAddress())
                .mail(adminRequest.getMail())
                .gender(adminRequest.getGender())
                .role(adminRequest.getRole())
                .build();
    }

    public static AdminResponse fromAdminToAdminResponse(Admin admin){
        return AdminResponse.builder()
                .name(admin.getName())
                .mobileNo(admin.getMobileNo())
                .address(admin.getAddress())
                .mail(admin.getMail())
                .build();
    }
}
