package org.com.orders.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrdersResponseDTO {
    private Integer orderId;
    private Integer customerId;
    private Timestamp orderDate;
    private double totalAmount;
    private List<OrderDetailsResponseDTO> orderDetails;
}
