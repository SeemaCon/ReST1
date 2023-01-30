package com.rest1.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rest1.product.model.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository 
extends JpaRepository<Product, Integer> {
	
	@Modifying
	@Query("Update Product set prodCost=:ProdCost where prodId=:id")
	public Integer updateProdCostById(Integer id, Double ProdCost);

}

