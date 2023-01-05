package com.toy.shop.service;

import com.toy.shop.domain.*;
import com.toy.shop.dto.OrderDto;
import com.toy.shop.exception.CommonException;
import com.toy.shop.repository.ItemJpaRepository;
import com.toy.shop.repository.MemberJpaRepository;
import com.toy.shop.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public OrderDto.Response save(OrderDto.SaveRequest requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new CommonException(MEMBER_NOT_FOUND));

        List<OrderItem> orderItems = new ArrayList<>();

        requestDto.getOrderItems().forEach(orderItem -> {
            Item item = itemRepository.findById(orderItem.getItemId()).orElseThrow(() -> new CommonException(ITEM_NOT_FOUND));

            orderItems.add(OrderItem.createOrderItem(item, item.getPrice(), orderItem.getCount()));
        });

        Delivery delivery = Delivery.createDelivery(requestDto);

        Order order = Order.createOrder(member, delivery, orderItems);

        orderRepository.save(order);

        return new OrderDto.Response(order);
    }

    @Override
    public List<OrderDto.Response> findAll(Long memberId, OrderStatus orderStatus, DeliveryStatus deliveryStatus) {
        List<Order> orders = orderRepository.findAll(memberId, orderStatus, deliveryStatus);

        return orders.stream()
                .map(OrderDto.Response::new)
                .toList();
    }
}
