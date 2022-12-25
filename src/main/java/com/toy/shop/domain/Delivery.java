package com.toy.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;

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

    public static Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();

        delivery.address = Address.createAddress(member.getAddress().getCity(), member.getAddress().getStreet(), member.getAddress().getZipcode());
        delivery.status = DeliveryStatus.READY;

        return delivery;
    }
}
