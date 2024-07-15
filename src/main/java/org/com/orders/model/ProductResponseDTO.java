package org.com.orders.model;

import lombok.Data;

@Data
public class ProductResponseDTO {

    private Integer productId;
    private String productName;
    private String description;
    private double price;
    private Integer stockQuantity;

}
