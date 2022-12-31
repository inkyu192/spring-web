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

    private int orderPrice;
    private int count;

    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();

        orderItem.item = item;
        orderItem.orderPrice = orderPrice;
        orderItem.count = count;

        item.removeQuantity(count);

        return orderItem;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
