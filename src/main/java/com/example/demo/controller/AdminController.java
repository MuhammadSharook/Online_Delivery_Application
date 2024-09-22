package com.example.demo.controller;

import com.example.demo.dto.request.AdminRequest;
import com.example.demo.dto.response.AdminResponse;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public ResponseEntity addAdmin(@RequestBody AdminRequest adminRequest){
        AdminResponse adminResponse = adminService.addAdmin(adminRequest);
        return new ResponseEntity(adminResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/mobile{mobile}")
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
    public ResponseEntity deleteAdmin(@PathVariable("mobileNo") String mobileNo){
        try{
            String response = adminService.deleteAdmin(mobileNo);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
