package com.toy.shop.service;

import com.toy.shop.domain.Member;
import com.toy.shop.dto.MemberSaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @PersistenceContext
    EntityManager em;

    @Test
    void save() {
        MemberSaveRequestDto requestDto = new MemberSaveRequestDto();
        requestDto.setName("박인규");
        requestDto.setCity("화성");
        requestDto.setStreet("순환대로");
        requestDto.setZipcode("000");

        Long saveId = memberService.save(requestDto).getId();

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, saveId);

        assertThat(saveId).isEqualTo(findMember.getId());
    }
}