package com.pms.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pms.dto.ItemRequestDTO;
import com.pms.dto.ItemResponseDTO;
import com.pms.dto.ProductRequestDTO;
import com.pms.dto.ProductResponseDTO;
import com.pms.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAll(
            @PageableDefault(size = 5) Pageable pageable) {

        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(
            @Valid @RequestBody ProductRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO dto) {

        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
    
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemResponseDTO>> getItems(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getItemsByProductId(id));
    }
    
    @PostMapping("/{id}/items")
    public ResponseEntity<ItemResponseDTO> addItem(@PathVariable Long id,@Valid @RequestBody ItemRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.addItemToProduct(id, dto));
    }


}
