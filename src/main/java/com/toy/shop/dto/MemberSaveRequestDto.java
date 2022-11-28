package com.toy.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSaveRequestDto {

    private String name;
    private String city;
    private String street;
    private String zipcode;
}
