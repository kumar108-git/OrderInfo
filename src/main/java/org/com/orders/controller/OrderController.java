package org.com.orders.controller;

import org.com.orders.model.OrdersRequestDTO;
import org.com.orders.model.OrdersResponseDTO;
import org.com.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrdersResponseDTO> createOrder(@RequestBody OrdersRequestDTO orderRequest) {
        OrdersResponseDTO createdOrder = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrdersResponseDTO> getOrderById(@PathVariable Integer orderId) {
        try {
            OrdersResponseDTO order = orderService.getOrderById(orderId);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrdersResponseDTO>> getAllOrders() {
        List<OrdersResponseDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Additional endpoint for getting orders by customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrdersResponseDTO>> getOrdersByCustomerId(@PathVariable Integer customerId) {
        List<OrdersResponseDTO> orders = orderService.getOrdersByCustomerId(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Additional endpoint for updating an existing order
//    @PutMapping("/{orderId}")
//    public ResponseEntity<OrdersResponseDTO> updateOrder(@PathVariable Integer orderId, @RequestBody OrdersRequestDTO orderRequest) {
//        try {
//            OrdersResponseDTO updatedOrder = orderService.updateOrder(orderId, orderRequest);
//            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // Additional endpoint for deleting an order
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}