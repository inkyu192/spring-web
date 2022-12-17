package com.toy.shop.service;

import com.toy.shop.domain.Member;
import com.toy.shop.dto.MemberResponseDto;
import com.toy.shop.dto.MemberSaveRequestDto;
import com.toy.shop.dto.MemberUpdateRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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

    @Test
    void findAll() {
        MemberSaveRequestDto requestDto1 = new MemberSaveRequestDto();
        requestDto1.setName("박인규");
        requestDto1.setCity("화성");
        requestDto1.setStreet("순환대로");
        requestDto1.setZipcode("000");

        Member saveMember1 = Member.createMember(requestDto1);

        entityManager.persist(saveMember1);

        MemberSaveRequestDto requestDto2 = new MemberSaveRequestDto();
        requestDto2.setName("박인규");
        requestDto2.setCity("화성");
        requestDto2.setStreet("순환대로");
        requestDto2.setZipcode("000");

        Member saveMember2 = Member.createMember(requestDto2);

        entityManager.persist(saveMember2);

        entityManager.flush();
        entityManager.clear();

        List<MemberResponseDto> list = memberService.findAll(null);

        List<Long> collect = list.stream()
                .map(MemberResponseDto::getId)
                .toList();

        assertThat(collect).containsOnly(saveMember1.getId(), saveMember2.getId());
    }

    @Test
    void findById() {
        MemberSaveRequestDto requestDto = new MemberSaveRequestDto();
        requestDto.setName("박인규");
        requestDto.setCity("화성");
        requestDto.setStreet("순환대로");
        requestDto.setZipcode("000");

        Member saveMember = Member.createMember(requestDto);

        entityManager.persist(saveMember);

        entityManager.flush();
        entityManager.clear();

        MemberResponseDto responseDto = memberService.findById(saveMember.getId());

        assertThat(saveMember.getId()).isEqualTo(responseDto.getId());
    }

    @Test
    void update() {
        MemberSaveRequestDto saveRequestDto = new MemberSaveRequestDto();
        saveRequestDto.setName("박인규");
        saveRequestDto.setCity("화성");
        saveRequestDto.setStreet("순환대로");
        saveRequestDto.setZipcode("000");

        Member saveMember = Member.createMember(saveRequestDto);

        entityManager.persist(saveMember);

        entityManager.flush();
        entityManager.clear();

        MemberUpdateRequestDto updateRequestDto = new MemberUpdateRequestDto();
        updateRequestDto.setCity("수원");

        MemberResponseDto responseDto = memberService.update(saveMember.getId(), updateRequestDto);

        assertThat(updateRequestDto.getCity()).isEqualTo(responseDto.getCity());
    }

    @Test
    void delete() {
        MemberSaveRequestDto saveRequestDto = new MemberSaveRequestDto();
        saveRequestDto.setName("박인규");
        saveRequestDto.setCity("화성");
        saveRequestDto.setStreet("순환대로");
        saveRequestDto.setZipcode("000");

        Member saveMember = Member.createMember(saveRequestDto);

        entityManager.persist(saveMember);

        entityManager.flush();
        entityManager.clear();

        memberService.delete(saveMember.getId());

        Member member = entityManager.find(Member.class, saveMember.getId());

        assertThat(member).isNull();
    }
}