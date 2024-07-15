package org.com.orders.repository;

import org.com.orders.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity,Integer> {
    List<OrdersEntity> findByCustomerId(Integer customerId);
}
