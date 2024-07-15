package org.com.orders.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrdersRequestDTO {
    private Integer orderId;
    private Integer customerId;
    private List<OrderDetailsRequestDTO> orderDetails;
}
