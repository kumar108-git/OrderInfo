package org.com.orders.model;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerResponseDTO {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date registrationDate;
}
