package com.toy.shop.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberUpdateRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @NotEmpty
    private String zipcode;
}
