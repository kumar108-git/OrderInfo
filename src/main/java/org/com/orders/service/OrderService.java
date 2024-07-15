package org.com.orders.service;

import org.com.orders.entity.OrdersEntity;
import org.com.orders.entity.OrderDetailsEntity;
import org.com.orders.model.OrdersRequestDTO;
import org.com.orders.model.OrdersResponseDTO;
import org.com.orders.model.OrderDetailsResponseDTO;
import org.com.orders.repository.CustomOrderRepositoryImpl;
import org.com.orders.repository.OrdersRepository;
import org.com.orders.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomOrderRepositoryImpl customOrderRepositoryImpl;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Transactional
    public OrdersResponseDTO createOrder(OrdersRequestDTO orderRequest) {
        OrdersEntity order = new OrdersEntity();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        // Save the order first to get the orderId
        order = ordersRepository.save(order);
        final Integer ordId = order.getOrderId();
        // Create and save order details
        List<OrderDetailsEntity> orderDetails = orderRequest.getOrderDetails().stream()
                .map(detailDTO -> {
                    OrderDetailsEntity detail = new OrderDetailsEntity();
                    detail.setOrderId(ordId);
                    detail.setProductId(detailDTO.getProductId());
                    detail.setQuantity(detailDTO.getQuantity());
                    detail.setUnitPrice(detailDTO.getUnitPrice());
                    return detail;
                })
                .collect(Collectors.toList());

        orderDetailsRepository.saveAll(orderDetails);

        // Calculate and set total amount
        double totalAmount = orderDetails.stream()
                .mapToDouble(detail -> detail.getQuantity() * detail.getUnitPrice())
                .sum();
        order.setTotalAmount(totalAmount);

        // Update the order with the total amount and order details
        order.setOrderDetails(orderDetails);
        order = ordersRepository.save(order);

        return entityToDto(order);
    }

    @Transactional
    public void deleteOrder(Integer orderId){
        OrdersEntity order = ordersRepository.findById(orderId).orElseThrow( () ->
                new RuntimeException(String.format("Order not found with order id : %s", orderId)));
        ordersRepository.delete(order);
    }

    public OrdersResponseDTO getOrderById(Integer orderId) {
        OrdersEntity order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return entityToDto(order);
    }

    public List<OrdersResponseDTO> getAllOrders() {
        List<OrdersEntity> orders = ordersRepository.findAll();
        return orders.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<OrdersResponseDTO> getOrdersByCustomerId(Integer customerId) {
        List<OrdersEntity> customerOrders = ordersRepository.findByCustomerId(customerId);
        return customerOrders.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<OrdersResponseDTO> getOrdersWithDetailsByCustomerId(Integer customerId) {
        List<OrdersEntity> customerOrders = customOrderRepositoryImpl.findOrdersWithDetailsByCustomerId(customerId);
        return customerOrders.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<OrdersResponseDTO> getOrdersByCustomerIdAndDateRange(Integer customerId, Timestamp startDate, Timestamp endDate) {
        List<OrdersEntity> customerOrders = customOrderRepositoryImpl.findOrdersByCustomerIdAndDateRange(customerId, startDate, endDate);
        return customerOrders.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }



    private OrdersResponseDTO entityToDto(OrdersEntity order) {
        OrdersResponseDTO dto = new OrdersResponseDTO();
        dto.setOrderId(order.getOrderId());
        dto.setCustomerId(order.getCustomerId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderDetailsResponseDTO> orderDetailsDTO = order.getOrderDetails().stream()
                .map(this::detailEntityToDto)
                .collect(Collectors.toList());
        dto.setOrderDetails(orderDetailsDTO);

        return dto;
    }

    private OrderDetailsResponseDTO detailEntityToDto(OrderDetailsEntity detail) {
        OrderDetailsResponseDTO dto = new OrderDetailsResponseDTO();
        dto.setOrderDetailsId(detail.getOrderDetailId());
        dto.setOrderId(detail.getOrderId());
        dto.setProductId(detail.getProductId());
        dto.setQuantity(detail.getQuantity());
        dto.setUnitPrice(detail.getUnitPrice());
        return dto;
    }
}