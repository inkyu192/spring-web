package com.toy.shop.service;

import com.toy.shop.domain.*;
import com.toy.shop.dto.OrderSaveRequestDto;
import com.toy.shop.exception.DataNotFoundException;
import com.toy.shop.repository.ItemJpaRepository;
import com.toy.shop.repository.ItemSpringJpaRepository;
import com.toy.shop.repository.MemberJpaRepository;
import com.toy.shop.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.toy.shop.common.ResultCode.ITEM_NOT_FOUND;
import static com.toy.shop.common.ResultCode.MEMBER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberJpaRepository memberRepository;
//    private final MemberSpringJpaRepository memberRepository;

    private final ItemJpaRepository itemRepository;
//    private final ItemSpringJpaRepository itemRepository;

    private final OrderJpaRepository orderRepository;

    @Override
    @Transactional
    public Long save(OrderSaveRequestDto requestDto) {
        // 회원 정보 조회
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new DataNotFoundException(MEMBER_NOT_FOUND));

        // 아이템 조회
        Item item = itemRepository.findById(requestDto.getItemId()).orElseThrow(() -> new DataNotFoundException(ITEM_NOT_FOUND));

        // 배송정보 생성
        Delivery delivery = Delivery.createDelivery(member);

        // 주문아이템 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, requestDto.getCount());

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return null;
    }
}
