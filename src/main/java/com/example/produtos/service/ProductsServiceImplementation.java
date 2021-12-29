package com.example.produtos.service;

import com.example.produtos.model.Products;
import com.example.produtos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.webjars.NotFoundException;
import org.springframework.data.domain.Pageable;


@Service
public class ProductsServiceImplementation implements ProductsService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Products createProduct(Products product) {
        Products products = repository.save(product);
        return product;
    }

    @Override
    public Object listAllProducts(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Products searchProductById(Long id) throws NotFoundException {
        Products products = idExists(id);
        return products;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = MethodArgumentNotValidException.class)
    public Products updateProduct(Long id, Integer quantity) throws NotFoundException {
        Products products = idExists(id);
        products.setQuantity(quantity);
        products = repository.save(products);
        return products;
    }

    @Override
    public void deleteProduct(Long id) throws NotFoundException {

        idExists(id);
        repository.deleteById(id);

    }

    private Products idExists(Long id) throws NotFoundException {
        Products products = repository.findById(id).orElse(null);
        if(products == null){
            throw new NotFoundException("Não foram encontrados produtos com o id"+ id);
        }
        return products;
    }
}
