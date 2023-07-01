package com.vti.shoppee.entity.dto;

import com.vti.shoppee.entity.entity.Account;
import com.vti.shoppee.entity.entity.Product;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class OrderCreateRequestDto {
    private int accountId;

    private int productId;

    private int quantity;
}
