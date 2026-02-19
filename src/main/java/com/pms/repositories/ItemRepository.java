package com.pms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Long>
{
	List<Item> findByProductId(Long productId);
}
