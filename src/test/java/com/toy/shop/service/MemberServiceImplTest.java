package com.toy.shop.service;

import com.toy.shop.domain.Member;
import com.toy.shop.dto.MemberSaveRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void save() {
        MemberSaveRequestDto requestDto = new MemberSaveRequestDto();
        requestDto.setName("박인규");
        requestDto.setCity("화성");
        requestDto.setStreet("순환대로");
        requestDto.setZipcode("000");

        Long saveId = memberService.save(requestDto).getId();

        entityManager.flush();
        entityManager.clear();

        Member findMember = entityManager.find(Member.class, saveId);

        assertThat(saveId).isEqualTo(findMember.getId());
    }
}