package com.pms.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pms.dto.ItemRequestDTO;
import com.pms.dto.ItemResponseDTO;
import com.pms.dto.ProductRequestDTO;
import com.pms.dto.ProductResponseDTO;

public interface ProductService 
{
    Page<ProductResponseDTO> getAllProducts(Pageable pageable);

    ProductResponseDTO getProductById(Long id);

    ProductResponseDTO createProduct(ProductRequestDTO dto);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);

    void deleteProduct(Long id);
    
    List<ItemResponseDTO> getItemsByProductId(Long productId);
    
    ItemResponseDTO addItemToProduct(Long productId, ItemRequestDTO dto);

}
