package org.com.orders.service;

import org.com.orders.entity.CustomerEntity;
import org.com.orders.model.CustomerRequestDTO;
import org.com.orders.model.CustomerResponseDTO;
import org.com.orders.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity createCustomer(CustomerRequestDTO customerRequestDTO){

        CustomerEntity customerEntity = this.customerRepository.save(this.dtoToEntity(customerRequestDTO));

        return customerEntity;
    }

    public CustomerResponseDTO getCustomerById(Integer customerId){
        CustomerEntity customerDetailsById = this.customerRepository.getReferenceById(customerId);

        return entityToDto(customerDetailsById);
    }

    private CustomerEntity dtoToEntity(final CustomerRequestDTO dto){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(dto.getCustomerId());
        customerEntity.setFirstName(dto.getFirstName());
        customerEntity.setLastName(dto.getLastName());
        customerEntity.setEmail(dto.getEmail());
        customerEntity.setPhoneNumber(dto.getPhoneNumber());
        customerEntity.setRegistrationDate(dto.getRegistrationDate());
        return customerEntity;
    }

    private CustomerResponseDTO entityToDto(final CustomerEntity customerEntity){
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setCustomerId(customerEntity.getCustomerId());
        customerResponseDTO.setFirstName(customerEntity.getFirstName());
        customerResponseDTO.setLastName(customerEntity.getLastName());
        customerResponseDTO.setEmail(customerEntity.getEmail());
        customerResponseDTO.setPhoneNumber(customerEntity.getPhoneNumber());
        customerResponseDTO.setRegistrationDate(customerEntity.getRegistrationDate());
        return customerResponseDTO;
    }

}
