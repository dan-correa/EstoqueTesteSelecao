package com.example.produtos.controller;

import com.example.produtos.model.Products;
import com.example.produtos.service.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/products",produces = {APPLICATION_JSON_VALUE})
public class ProductResource {
    @Autowired
    ProductsService service;

    @PostMapping
   public ResponseEntity<Products> postProduct(@Valid @RequestBody Products product) {
        Products products = service.createProduct(product);
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "field", defaultValue = "id") String field,
            @RequestParam(value = "sort", defaultValue = "asc") String order) {

        return ResponseEntity.ok(service.listAllProducts(PageRequest.of(page,limit, Sort.Direction.fromString(order), field)));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) throws NotFoundException {
        Products products = service.searchProductById(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> putProduct(@PathVariable Long id, @RequestParam Integer quantity) throws NotFoundException {
        Products products = service.updateProduct(id, quantity);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws NotFoundException {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }








}
