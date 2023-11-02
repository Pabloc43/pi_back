package com.grupo3.digitalbook.demo.repository;

import com.grupo3.digitalbook.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
