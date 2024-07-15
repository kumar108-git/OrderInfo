package org.com.orders.repository;

import org.com.orders.entity.OrdersEntity;

import java.sql.Timestamp;
import java.util.List;

public interface CustomOrderRepository {
    List<OrdersEntity> findOrdersWithDetailsByCustomerId(Integer customerId);
    List<OrdersEntity> findOrdersByCustomerIdAndDateRange(Integer customerId, Timestamp startDate, Timestamp endDate);
}