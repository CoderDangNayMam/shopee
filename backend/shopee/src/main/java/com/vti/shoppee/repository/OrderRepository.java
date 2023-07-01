package com.vti.shoppee.repository;

import com.vti.shoppee.entity.entity.Account;
import com.vti.shoppee.entity.entity.Order;
import com.vti.shoppee.entity.entity.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByStatusAndAccount_Id(StatusOrder status, int accountId);

    List<Order> findAllByAccount_Id(int accountId);

//    @Query(value = "select o from Order o where o.status = :status and o.account = :account")
//    List<Order> findAllByStatusV2(@Param("status") StatusOrder status,@Param("accountId") Account account);

    @Query(value = "select * from Order o where o.status = :status", nativeQuery = true)
    List<Order> findAllByStatusV3(@Param("status") StatusOrder status);
}
