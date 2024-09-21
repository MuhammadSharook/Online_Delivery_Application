package com.example.demo.utils;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils {
    final VendorRepository vendorRepository;

    @Autowired
    public ValidationUtils(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public boolean validateVendor(int id){
        Optional<Vendor> vendorOptional = vendorRepository.findById(id);
        return vendorOptional.isPresent();
    }
}
