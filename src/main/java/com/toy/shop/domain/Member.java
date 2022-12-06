package com.toy.shop.domain;

import com.toy.shop.dto.MemberSaveRequestDto;
import com.toy.shop.dto.MemberUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    public static Member createMember(MemberSaveRequestDto requestDto) {
        Member member = new Member();

        member.name = requestDto.getName();
        member.address = Address.createAddress((requestDto.getCity()), requestDto.getStreet(), requestDto.getZipcode());

        return member;
    }

    public void updateMember(MemberUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.address = Address.createAddress((requestDto.getCity()), requestDto.getStreet(), requestDto.getZipcode());
    }
}
