package com.toy.shop.domain;

import com.toy.shop.dto.MemberSaveRequestDto;
import com.toy.shop.dto.MemberUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Entity
@Getter
public class Member extends BaseDomain {

    @Id @GeneratedValue
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
        if (StringUtils.hasText(requestDto.getName())) this.name = requestDto.getName();
        this.address.updateAddress((requestDto.getCity()), requestDto.getStreet(), requestDto.getZipcode());
    }
}
