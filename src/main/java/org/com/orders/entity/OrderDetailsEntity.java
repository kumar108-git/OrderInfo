package org.com.orders.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "OrderDetails", schema = "sch")
@ToString
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_order_detail_id_seq_generator")
    @SequenceGenerator(name = "order_details_order_detail_id_seq_generator", sequenceName = "order_details_order_detail_id_seq", allocationSize = 1, schema = "sch")
    private Integer orderDetailId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private double unitPrice;

}
