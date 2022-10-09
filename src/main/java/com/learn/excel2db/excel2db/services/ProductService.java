package com.learn.excel2db.excel2db.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learn.excel2db.excel2db.entities.Products;
import com.learn.excel2db.excel2db.helper.Helper;
import com.learn.excel2db.excel2db.repositories.ProductRepo;

@Service
public class ProductService {

	
	@Autowired
	private ProductRepo productRepo;
	
	public void save(MultipartFile file) {
		List<Products> products;
		try {
			products = Helper.convertExcelToListOfProduct(file.getInputStream());
			this.productRepo.saveAll(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Products> getAllProducts(){
		return this.productRepo.findAll();
		
	}
	
}
