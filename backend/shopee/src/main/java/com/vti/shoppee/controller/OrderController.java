package com.vti.shoppee.controller;

import com.vti.shoppee.entity.dto.OrderCreateRequestDto;
import com.vti.shoppee.entity.dto.OrderUpdateDto;
import com.vti.shoppee.entity.dto.ProductCreateRequestDto;
import com.vti.shoppee.entity.dto.ProductUpdateDto;
import com.vti.shoppee.entity.entity.Account;
import com.vti.shoppee.entity.entity.Order;
import com.vti.shoppee.entity.entity.Product;
import com.vti.shoppee.entity.entity.StatusOrder;
import com.vti.shoppee.service.iml.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin("*")
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Order create(@RequestBody @Valid OrderCreateRequestDto dto) {
        return orderService.create(dto);
    }
    @PostMapping("/buy/{orderId}")
    public Order purchaseOrder(@PathVariable int orderId){
        return orderService.purchaseOrder(orderId);
    }
    @PostMapping("/cancel/{orderId}")
    public Order cancelOrder(@PathVariable int orderId){
        return orderService.cancelOrder(orderId);
    }
    @GetMapping("/get-all")
    public List<Order> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/get-by-status")
    public List<Order> findAllByStatus(@RequestParam(required = false) StatusOrder status,@RequestParam int accountId){
        return orderService.findByStatus(status, accountId);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@RequestParam int id){
        orderService.deleteById(id);
    }
}
