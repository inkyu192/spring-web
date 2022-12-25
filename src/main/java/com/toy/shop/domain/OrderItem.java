package com.toy.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "order_item")
public class OrderItem extends BaseDomain {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int price;
    private int count;

    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();

        orderItem.item = item;
        orderItem.price = item.getPrice();
        orderItem.count = count;

        return orderItem;
    }
}
