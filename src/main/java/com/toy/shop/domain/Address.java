package com.toy.shop.domain;

import com.toy.shop.dto.MemberUpdateRequestDto;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public static Address createAddress(String city, String street, String zipcode) {
        Address address = new Address();

        if (StringUtils.hasText(city)) address.city = city;
        if (StringUtils.hasText(street)) address.street = street;
        if (StringUtils.hasText(zipcode)) address.zipcode = zipcode;

        return address;
    }

    public void updateAddress(String city, String street, String zipcode) {
        if (StringUtils.hasText(city)) this.city = city;
        if (StringUtils.hasText(street)) this.street = street;
        if (StringUtils.hasText(zipcode)) this.zipcode = zipcode;
    }
}
