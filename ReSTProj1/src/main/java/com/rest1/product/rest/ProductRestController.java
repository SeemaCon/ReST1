package com.rest1.product.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest1.product.exception.ProductNotFoundException;
import com.rest1.product.model.Product;
import com.rest1.product.service.IProductService;
import com.rest1.product.util.utilityProd;
@RestController
@RequestMapping("/products")
public class ProductRestController {
	
	private final static Logger LOG= LoggerFactory.getLogger(ProductRestController.class);
	@Autowired
	private IProductService service;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(
			@RequestBody Product product)
	{
		LOG.info("Enter into saveProduct Method");
		ResponseEntity<String> resp= null;
		try
		{
			Integer id =  service.saveProduct(product);
			resp = new ResponseEntity<String>(new StringBuffer()
											.append("Product saved")
											.append(id).toString()
											,HttpStatus.CREATED);
			LOG.info("Saved Product with Id {}", id);
		}catch (Exception e){
			resp = new ResponseEntity<String>("Unable to save product",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			LOG.error("Unable to save product");
		}
		LOG.info("Leaving saveProduct Method");
		return resp;		
	
	}
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		LOG.info("Enter into getAllProducts Method");
		ResponseEntity<List<Product>> resp = null;
		try {
		List<Product> list =  service.getAllProducts();
		
		//resp= new ResponseEntity<List<Product>>(list,HttpStatus.OK);
		//equal to 
		resp = ResponseEntity.ok(list);
		LOG.info("Sucessfully fatch data");
		}
		catch(ProductNotFoundException pne)
		{
			LOG.error("Error in fatching data");
			throw pne;
			
		}
		catch(Exception e) {
			resp = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			LOG.error("Error in fatching data");
		}
		LOG.info("Leaving getAllProducts Method");
		return resp;
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<?> findProductByid(
			@PathVariable Integer id
			)
	{
		LOG.info("Enter into findProductByid Method for Id {}", id);
		ResponseEntity<?> resp = null;
		
		try {
			Product p = service.getProductDetailById(id);
			resp = new ResponseEntity<Product>(
					p,HttpStatus.OK);
			LOG.info("Successfully fatch data for id {}",id);
			
		}  catch(ProductNotFoundException pne) {
			//send to Custom Exception Handler
			//throw it to handler
			LOG.error("unable fatching data for id {}",id);
			throw pne;
		} catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to fetch Product",
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			LOG.error("Error in fatching data for id {}",id);
		}
		LOG.info("Leaving findProductByid");
		return resp;
	}	
	
	@DeleteMapping("del/{id}")
	public ResponseEntity<String> deleteProduct(
				@PathVariable Integer id
			)
	{
		LOG.info("Enter into deleteProduct Method for id {}",id);
		ResponseEntity<String> resp = null;
		try {
			service.deleteProduct(id);
			resp =ResponseEntity.ok("Product deleted");
			LOG.info("Successfully deleted data for id {}",id);
		}catch(ProductNotFoundException pne) {
			LOG.error("Unable to fatch data for id{}",id);
			throw pne;
		}
		catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to Delete Product", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			LOG.error("Error in delete data for id{}",id);
		}
		LOG.info("Leaving deleteProduct");
		return resp;
	}
	
	
	@PutMapping("update/{id}")
	public ResponseEntity<String> updateProduct(
			@PathVariable Integer id,
			@RequestBody Product product
			)
	{
		LOG.info("Enter into updateProduct Method for ID {}",id);
		ResponseEntity<String> resp = null;
		try {
			Product fromdb = service.getProductDetailById(id);
			utilityProd.copyNonNullValues(fromdb, product);
			service.updateProduct(fromdb);
			LOG.info("Successfully update ID{}",id);
			resp = new ResponseEntity<String>("Product Updated!!",HttpStatus.RESET_CONTENT);
		} catch(ProductNotFoundException pne) {
			LOG.error("Unable to fatch data for id{}",id);
			throw  pne;
		}catch (Exception e)
		{
			resp = new ResponseEntity<String>("Unable to update!!",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			LOG.error("Error in update data for id{}",id);
		}
		LOG.info("Leaving updateProduct Method");
		return resp;
	}
	@PatchMapping("updatecost/{id}/{newcost}")
	public ResponseEntity<String> updateProductCost(
			@PathVariable Integer id,
			@PathVariable Double newcost
			)
	{	
		LOG.info("Enter into updateProductCost Method for ID{}",id);
		ResponseEntity<String> resp = null;
		try {
			service.updateProdCostById(id, newcost);
			resp=  new ResponseEntity<String>("Product Updated!!",HttpStatus.PARTIAL_CONTENT);
			LOG.info("Successfully update cost for ID{}",id);
		} catch (ProductNotFoundException pne) {
			LOG.error("Unable to fatch data for id{}",id);
			throw pne;
		}
		catch(Exception e) {
			LOG.error("Error in update data for id{}",id);
			resp = new ResponseEntity<String>("Unable to update!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("Leaving updateProductCost Method");
		return resp;
	}
	
}


