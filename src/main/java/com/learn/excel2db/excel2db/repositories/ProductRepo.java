package com.learn.excel2db.excel2db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.excel2db.excel2db.entities.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {

}
