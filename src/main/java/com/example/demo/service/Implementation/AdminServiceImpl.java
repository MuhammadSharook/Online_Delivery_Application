package com.example.demo.service.Implementation;

import com.example.demo.dto.request.AdminRequest;
import com.example.demo.dto.response.AdminResponse;
import com.example.demo.exception.AdminNotFoundException;
import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import com.example.demo.transformer.AdminTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminResponse addAdmin(AdminRequest adminRequest) {
        Admin admin = AdminTransformer.fromAdminRequestToAdmin(adminRequest);

        Admin savedAdmin = adminRepository.save(admin);

        return AdminTransformer.fromAdminToAdminResponse(savedAdmin);
    }

    @Override
    public AdminResponse findAdminByMobileNo(String mobileNo) {
        Admin admin = adminRepository.findByMobileNo(mobileNo);

        if(admin == null){
            throw new AdminNotFoundException("Invalid Mobile No !!!");
        }
        return AdminTransformer.fromAdminToAdminResponse(admin);
    }

    @Override
    public String deleteAdmin(String mobileNo) {
        Admin admin = adminRepository.findByMobileNo(mobileNo);

        if(admin == null){
            throw new AdminNotFoundException("Admin not found!!!");
        }

        adminRepository.delete(admin);

        return "Successfully deleted.";
    }
}
