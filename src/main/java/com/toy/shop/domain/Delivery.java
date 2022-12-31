package com.toy.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static com.toy.shop.dto.OrderDto.*;

@Entity
@Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public static Delivery createDelivery(SaveRequest requestDto) {
        Delivery delivery = new Delivery();

        delivery.address = Address.createAddress(requestDto.getCity(), requestDto.getStreet(), requestDto.getZipcode());
        delivery.status = DeliveryStatus.READY;

        return delivery;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
