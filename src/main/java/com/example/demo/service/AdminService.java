package com.example.demo.service;

import com.example.demo.dto.request.AdminRequest;
import com.example.demo.dto.response.AdminResponse;

public interface AdminService {
    AdminResponse addAdmin(AdminRequest adminRequest);

    AdminResponse findAdminByMobileNo(String mobileNo);
}
