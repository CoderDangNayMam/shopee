package com.vti.shoppee.service;

import com.vti.shoppee.entity.dto.OrderCreateRequestDto;
import com.vti.shoppee.entity.dto.OrderUpdateDto;
import com.vti.shoppee.entity.entity.Account;
import com.vti.shoppee.entity.entity.Order;
import com.vti.shoppee.entity.entity.StatusOrder;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();

    List<Order> findByStatus(StatusOrder statusOrder, int accountId);

    Order create(OrderCreateRequestDto dto);

    Order purchaseOrder(int orderId);

    Order cancelOrder(int orderId);

    void deleteById(int id);

    Order update(OrderUpdateDto dto);

    Order getById(int id);
}
