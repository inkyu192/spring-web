package com.toy.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public static Address createAddress(String city, String street, String zipcode) {
        Address address = new Address();

        address.city = city;
        address.street = street;
        address.zipcode = zipcode;

        return address;
    }
}
