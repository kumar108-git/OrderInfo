package org.com.orders.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.com.orders.entity.OrdersEntity;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrdersEntity> findOrdersWithDetailsByCustomerId(Integer customerId) {
        String jpql = "SELECT DISTINCT o FROM Orders o LEFT JOIN FETCH o.orderDetails WHERE o.customerId = :customerId";
        TypedQuery<OrdersEntity> query = entityManager.createQuery(jpql, OrdersEntity.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    @Override
    public List<OrdersEntity> findOrdersByCustomerIdAndDateRange(Integer customerId, Timestamp startDate, Timestamp endDate) {
        String jpql = "SELECT o FROM Orders o WHERE o.customerId = :customerId AND o.orderDate BETWEEN :startDate AND :endDate";
        TypedQuery<OrdersEntity> query = entityManager.createQuery(jpql, OrdersEntity.class);
        query.setParameter("customerId", customerId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}