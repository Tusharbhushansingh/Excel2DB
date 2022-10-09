package com.learn.excel2db.excel2db.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learn.excel2db.excel2db.entities.Products;
import com.learn.excel2db.excel2db.helper.Helper;
import com.learn.excel2db.excel2db.services.ProductService;

@RestController
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

		if (Helper.checkExcelFormat(file)) {
			// true

			this.productService.save(file);
			return ResponseEntity.ok(Map.of("message", "File is uploaded to Database!!"));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel format file only.");
	}

	
	@GetMapping("/products")
	public List<Products> getAllProduct(){
		return this.productService.getAllProducts();
	}
}
