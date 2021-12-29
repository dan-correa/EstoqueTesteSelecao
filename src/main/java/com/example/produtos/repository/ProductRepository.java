package com.example.produtos.repository;

import com.example.produtos.model.Products;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository  extends PagingAndSortingRepository<Products, Long> {

}