package com.example.produtos.service;

import com.example.produtos.model.Products;
import org.webjars.NotFoundException;

import org.springframework.data.domain.Pageable;


public interface ProductsService {
    public Products createProduct(Products product);
    public Object listAllProducts(Pageable page);
    public Products searchProductById(Long id) throws NotFoundException;
    public Products updateProduct(Long id, Integer quantity) throws NotFoundException;
    public void deleteProduct(Long id) throws NotFoundException;
}
