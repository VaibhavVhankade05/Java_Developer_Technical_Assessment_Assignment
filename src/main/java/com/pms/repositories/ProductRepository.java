package com.pms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}
