package com.toy.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberUpdateRequestDto {

    private String name;

    private String city;

    private String street;

    private String zipcode;
}
