package com.vti.shoppee.entity.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`activeUser`")
public class ActiveUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private int id;

    @Column(name = "uuid", unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Account account;
}
