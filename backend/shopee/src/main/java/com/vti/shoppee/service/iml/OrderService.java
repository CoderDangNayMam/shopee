package com.vti.shoppee.service.iml;

import com.vti.shoppee.config.exception.AppException;
import com.vti.shoppee.config.exception.ErrorResponseEnum;
import com.vti.shoppee.entity.dto.OrderCreateRequestDto;
import com.vti.shoppee.entity.dto.OrderUpdateDto;
import com.vti.shoppee.entity.entity.Account;
import com.vti.shoppee.entity.entity.Order;
import com.vti.shoppee.entity.entity.Product;
import com.vti.shoppee.entity.entity.StatusOrder;
import com.vti.shoppee.repository.AccountRepository;
import com.vti.shoppee.repository.OrderRepository;
import com.vti.shoppee.repository.ProductRepository;
import com.vti.shoppee.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByStatus(StatusOrder status, int accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
        if (status != null) {
            return orderRepository.findAllByStatusAndAccount_Id(status, accountId);
        } else {
            return orderRepository.findAllByAccount_Id(accountId);
        }
    }

    @Override
    public Order create(OrderCreateRequestDto dto) {
        Optional<Account> accountOptional = accountRepository.findById(dto.getAccountId());
        Optional<Product> productOptional = productRepository.findById(dto.getProductId());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_ACCOUNT);
        }
        if (productOptional.isEmpty()) {
            throw new AppException(ErrorResponseEnum.NOT_FOUND_PRODUCT);
        }
        Account account = accountOptional.get();
        Product product = productOptional.get();
        Order order = new Order();
        order.setAccount(account);
        order.setProduct(product);
        order.setQuantity(dto.getQuantity());
        order.setStatus(StatusOrder.PENDING);
        return orderRepository.save(order);

    }

    @Override
    public Order purchaseOrder(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(StatusOrder.DONE);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    @Override
    public Order cancelOrder(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(StatusOrder.CANCEL);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            orderRepository.deleteById(id);
        }
    }

    @Override
    public Order update(OrderUpdateDto dto) {
        Optional<Order> optionalOrder = orderRepository.findById(dto.getId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            BeanUtils.copyProperties(dto, order);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    @Override
    public Order getById(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            return null;
        }
    }
}
