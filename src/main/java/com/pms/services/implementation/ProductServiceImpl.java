package com.pms.services.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pms.dto.ItemRequestDTO;
import com.pms.dto.ItemResponseDTO;
import com.pms.dto.ProductRequestDTO;
import com.pms.dto.ProductResponseDTO;
import com.pms.entities.Item;
import com.pms.entities.Product;
import com.pms.exceptions.ResourceNotFoundException;
import com.pms.repositories.ItemRepository;
import com.pms.repositories.ProductRepository;
import com.pms.services.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;
    
    private final ItemRepository itemRepository;

    @Override
    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        return mapToDTO(product);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        Product product = Product.builder()
                .productName(dto.getProductName())
                .createdBy("ADMIN")
                .createdOn(LocalDateTime.now())
                .build();

        Product saved = productRepository.save(product);

        return mapToDTO(saved);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        product.setProductName(dto.getProductName());
        product.setModifiedBy("ADMIN");
        product.setModifiedOn(LocalDateTime.now());

        Product updated = productRepository.save(product);

        return mapToDTO(updated);
    }

    @Override
    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }

        productRepository.deleteById(id);
    }

    //  Mapping Method
    private ProductResponseDTO mapToDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .createdBy(product.getCreatedBy())
                .createdOn(product.getCreatedOn())
                .build();
    }
    
    
    @Override
    public List<ItemResponseDTO> getItemsByProductId(Long productId) {

        // First check product exists
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id " + productId);
        }

        return itemRepository.findByProductId(productId)
                .stream()
                .map(item -> ItemResponseDTO.builder()
                        .id(item.getId())
                        .quantity(item.getQuantity())
                        .build())
                .toList();
    }

    
    @Override
    public ItemResponseDTO addItemToProduct(Long productId, ItemRequestDTO dto) {

        // Check product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Product not found with id " + productId));

        // Create item
        Item item = Item.builder()
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .product(product)
                .build();

        Item savedItem = itemRepository.save(item);

        return ItemResponseDTO.builder()
                .id(savedItem.getId())
                .name(savedItem.getName())
                .quantity(savedItem.getQuantity())
                .build();
    }

}
