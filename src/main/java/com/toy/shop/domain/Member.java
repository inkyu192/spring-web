package com.toy.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.toy.shop.dto.MemberDto.Update;
import static com.toy.shop.dto.MemberDto.Save;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseDomain {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public static Member createMember(Save requestDto) {
        Member member = new Member();

        member.name = requestDto.getName();
        member.address = Address.createAddress((requestDto.getCity()), requestDto.getStreet(), requestDto.getZipcode());

        return member;
    }

    public void updateMember(Update requestDto) {
        if (StringUtils.hasText(requestDto.getName())) this.name = requestDto.getName();
        this.address.updateAddress((requestDto.getCity()), requestDto.getStreet(), requestDto.getZipcode());
    }
}
