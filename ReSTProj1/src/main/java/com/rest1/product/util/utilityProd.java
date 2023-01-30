package com.rest1.product.util;

import com.rest1.product.model.Product;

public interface utilityProd {
	public static void copyNonNullValues(
			Product fromdb, Product newDbrequest) 
	{
		//copy non-null values
		if(newDbrequest.getProdCode()!=null)
			fromdb.setProdCode(newDbrequest.getProdCode());
		if(newDbrequest.getProdCost()!=null)
			fromdb.setProdCost(newDbrequest.getProdCost());
	}
}
