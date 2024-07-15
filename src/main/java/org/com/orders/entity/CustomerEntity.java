package org.com.orders.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Entity
@Table(name="Customer", schema = "sch")
@ToString
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_customer_id_seq_seq_generator")
    @SequenceGenerator(name = "customers_customer_id_seq_seq_generator", sequenceName = "customers_customer_id_seq", allocationSize = 1, schema = "sch")
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date registrationDate;
}
