package org.com.orders.service;

import org.com.orders.entity.ProductEntity;
import org.com.orders.model.ProductRequestDTO;
import org.com.orders.model.ProductResponseDTO;
import org.com.orders.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductEntity createProduct(ProductRequestDTO productRequestDTO) {
        ProductEntity productEntity = this.productRepository.save(this.dtoToEntity(productRequestDTO));
        return productEntity;
    }

    public ProductResponseDTO getProductById(Integer productId) {
        ProductEntity productDetailsById = this.productRepository.getReferenceById(productId);
        return entityToDto(productDetailsById);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<ProductEntity> products = this.productRepository.findAll();
        return products.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private ProductEntity dtoToEntity(final ProductRequestDTO dto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(dto.getProductId());
        productEntity.setProductName(dto.getProductName()); // Convert Integer to String
        // Note: Other fields like description, price, and stockQuantity are not set here
        // because they are not present in the ProductRequestDTO
        return productEntity;
    }

    private ProductResponseDTO entityToDto(final ProductEntity productEntity) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(productEntity.getProductId());
        productResponseDTO.setProductName(productEntity.getProductName());
        productResponseDTO.setDescription(productEntity.getDescription());
        productResponseDTO.setPrice(productEntity.getPrice());
        productResponseDTO.setStockQuantity(productEntity.getStockQuantity());
        return productResponseDTO;
    }
}