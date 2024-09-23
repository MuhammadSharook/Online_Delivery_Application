package com.example.demo.repository;

import com.example.demo.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Integer> {
    public  DeliveryPartner findByMobileNo(String mobileNo);

    String findRandomDeliveryPartner = "select * from delivery_partner Order By RANDOM() LIMIT 1";
    @Query(value = findRandomDeliveryPartner,nativeQuery = true)
    DeliveryPartner findRandomDeliveryPartner();
}
