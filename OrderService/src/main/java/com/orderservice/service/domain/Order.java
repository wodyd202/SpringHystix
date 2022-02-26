package com.orderservice.service.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderer;

    @ElementCollection
    private List<OrderLine> orderLines;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @CreatedDate
    private LocalDateTime createDatedTime;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Order(String orderer, List<OrderLine> orderLines) {
        this.orderer = orderer;
        this.orderLines = orderLines;
        this.orderState = OrderState.ORDER;
    }

    public static Order of(String orderer, List<OrderLine> orderLines){
        return new Order(orderer, orderLines);
    }

    public Long getId(){
        return id;
    }
}
