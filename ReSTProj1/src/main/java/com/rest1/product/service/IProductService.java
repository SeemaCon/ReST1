package com.rest1.product.service;

import java.util.List;

import com.rest1.product.model.Product;

public interface IProductService {
	Integer saveProduct(Product p);
	List<Product> getAllProducts();
	Product getProductDetailById(Integer id);
	void deleteProduct(Integer id);
	boolean isProductExist(Integer id);
	void updateProduct(Product p);
	Integer updateProdCostById(Integer id, Double ProdCost);
	
}
