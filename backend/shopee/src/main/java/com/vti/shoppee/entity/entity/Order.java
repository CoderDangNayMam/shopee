package com.vti.shoppee.entity.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`order`")
public class Order extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_by") // tên của khoá ngoại trong database
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL) // tên của khoá ngoại trong database
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOrder status;
}
