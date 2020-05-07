package com.coodcool.icook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String country;
    private String city;
    private String address;
    private String zipCode;

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;


}
