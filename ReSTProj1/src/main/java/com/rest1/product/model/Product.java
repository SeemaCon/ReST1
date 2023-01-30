package com.rest1.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue
	private Integer prodId;
	
	private String prodCode;
	private Double prodCost;
	
	private Double prodGst;
	private Double prodDisc;
	
}

