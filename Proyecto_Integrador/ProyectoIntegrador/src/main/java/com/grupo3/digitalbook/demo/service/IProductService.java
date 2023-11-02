package com.grupo3.digitalbook.demo.service;

import com.grupo3.digitalbook.demo.entity.Product;
import com.grupo3.digitalbook.demo.exception.BadRequestException;
import com.grupo3.digitalbook.demo.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product createProduct(Product product) throws BadRequestException;

    public Product updateProduct(Long id, Product productNew) throws BadRequestException, ResourceNotFoundException;

    public void deleteProduct(Long id) throws ResourceNotFoundException;

    public Optional<Product> findProductById(Long id) throws ResourceNotFoundException;

    List<Product> getAllProducts();
}

