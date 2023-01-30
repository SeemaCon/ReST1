package com.rest1.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest1.product.exception.ProductNotFoundException;
import com.rest1.product.model.Product;
import com.rest1.product.repo.ProductRepository;
import com.rest1.product.service.IProductService;

import jakarta.transaction.Transactional;
@Service
public class productServiceImpl implements IProductService {

	@Autowired
	private ProductRepository repo;
	
	@Override
	public Integer saveProduct(Product p) {
		//JDK 10 : local variable type inference
		//Best DataType is selected by Java compiler
		var cost = p.getProdCost();
		if (cost!=null && cost>0)
		{
		var gst = cost * 12.0/100;
		var disc = cost * 20.0/100;
		
		p.setProdGst(gst);
		p.setProdDisc(disc);
		}
		
		p = repo.save(p);
		
		return p.getProdId();
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list =  repo.findAll();
		return list;
	}

	@Override
	public Product getProductDetailById(Integer id) {
		
		/*
		//Try to read object from DB
		Optional<Product> opt = repo.findById(id);
		
		if(opt.isPresent()) { //if object is present
			Product p = opt.get();
			return p;
		} else { //object not exist
			throw new ProductNotFoundException("Product '"+id+"' Not Exist");
		}
		*/
		return repo.findById(id)
				.orElseThrow(
						()-> new ProductNotFoundException(
								"Product '"+id+"' Not Exist")
						);
		
		
	}
	public void deleteProduct(Integer id) {
		Product p = getProductDetailById(id);
		repo.delete(p);
	}
	
	public boolean isProductExist(Integer id) {
		return repo.existsById(id);
	}

	
	public void updateProduct(Product p) {
		var cost = p.getProdCost();
		if (cost!=null && cost>0)
		{
		var gst = cost * 12.0/100;
		var disc = cost * 20.0/100;

		p.setProdGst(gst);
		p.setProdDisc(disc);
		}
		repo.save(p);

	}

	@Transactional
	@Override
	public Integer updateProdCostById(Integer id, Double ProdCost) {
		// TODO Auto-generated method stub
		if (isProductExist(id))
			throw new ProductNotFoundException(new StringBuffer()
							.append("product - ").append(id)
							.append(" not found").toString());
		else
			return repo.updateProdCostById(id, ProdCost);
	}
}
