package com.vti.shoppee.entity.dto;

import com.vti.shoppee.entity.entity.Account;
import com.vti.shoppee.entity.entity.Product;
import com.vti.shoppee.entity.entity.StatusOrder;
import lombok.Data;

import javax.persistence.*;
@Data
public class OrderUpdateDto {
    private int id;

    private int accountId;

    private int productId;

    private int quantity;

    private StatusOrder status;
}
