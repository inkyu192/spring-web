package com.toy.shop.domain;

import com.toy.shop.dto.MemberSaveRequestDto;
import lombok.Getter;

import javax.persistence.*;

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
}
