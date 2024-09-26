package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.dto.request.AdminRequest;
import com.example.demo.dto.response.AdminResponse;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final AdminService adminService;
    private final CustomerService customerService;
    private final DeliveryPartnerService deliveryPartnerService;
    private final VendorService vendorService;
    private final OrderService orderService;

    @Autowired
    public AdminController(AdminService adminService,
                           CustomerService customerService,
                           DeliveryPartnerService deliveryPartnerService,
                           VendorService vendorService,
                           OrderService orderService) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.deliveryPartnerService = deliveryPartnerService;
        this.vendorService = vendorService;
        this.orderService = orderService;
    }


    public String dashboard()
    {
        return "admin/dashboard";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity addAdmin(@RequestBody AdminRequest adminRequest){
        AdminResponse adminResponse = adminService.addAdmin(adminRequest);
        return new ResponseEntity(adminResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/mobile{mobile}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public  ResponseEntity findAdminByMobileNo (@PathVariable("mobile") String mobileNo){
        try{
            AdminResponse adminResponse = adminService.findAdminByMobileNo(mobileNo);
            return new ResponseEntity(adminResponse,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{mobileNo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteAdmin(@PathVariable("mobileNo") String mobileNo){
        try{
            String response = adminService.deleteAdmin(mobileNo);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
