package org.com.orders.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="Product", schema = "sch")
@ToString
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_product_seq_generator")
    @SequenceGenerator(name = "products_product_seq_generator", sequenceName = "products_product_id_seq", allocationSize = 1, schema = "sch")
    private Integer productId;
    private String productName;
    private String description;
    private double price;
    private Integer stockQuantity;

}
