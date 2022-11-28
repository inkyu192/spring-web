package com.toy.shop.dto;

import com.toy.shop.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.city = member.getAddress().getCity();
        this.street = member.getAddress().getStreet();
        this.zipcode = member.getAddress().getZipcode();
    }
}
