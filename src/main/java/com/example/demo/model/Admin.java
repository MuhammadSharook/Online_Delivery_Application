package com.example.demo.model;

import com.example.demo.Enum.Gender;
import com.example.demo.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.admin.SpringApplicationAdminMXBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2,  message = "{validation.name.size.too_short}")
    @Size(max = 16,  message = "{validation.name.size.too_long}")
    String name;

    @Column(unique = true,nullable = false)
    @Size(min = 10,max = 10)
    String mobileNo;

    @Email
    @NotNull
    @Column(unique = true)
    String mail;

    String address;

    String password;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    List<Customer> user = new ArrayList<>();

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    List<ProductItem> productItems = new ArrayList<>();

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    List<ListItem> listItems = new ArrayList<>();

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    List<DeliveryPartner> deliveryPartners = new ArrayList<>();

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();

}
