package com.grupo3.digitalbook.demo.controller;

import com.grupo3.digitalbook.demo.entity.Product;
import com.grupo3.digitalbook.demo.entity.ProductImage;
import com.grupo3.digitalbook.demo.exception.BadRequestException;
import com.grupo3.digitalbook.demo.exception.ResourceNotFoundException;
import com.grupo3.digitalbook.demo.service.impl.ProductImageServiceImpl;
import com.grupo3.digitalbook.demo.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private ProductImageServiceImpl productImageServiceImpl;

    private final static Logger LOGGER = Logger.getLogger(ProductController.class);


    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(
            @RequestBody @Valid Product product) throws BadRequestException {

        Set<ProductImage> productImages = product.getProductImages();

        product.setProductImages(null);
        Product createdProduct = productServiceImpl.createProduct(product);

        if (productImages != null && !productImages.isEmpty()) {
            for (ProductImage productImage : productImages) {
                productImage.setProduct(createdProduct);
                productImageServiceImpl.createProductImage(productImage);
            }
        }

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productNew) {
        try {
            // Si vienen cargadas, almacenar caracteristicas en una variable
            // Si vienen cargadas, almacenar images en una variable

            // Pasar caracteristicas y imagenes de productNew a null

            // Obtener producto de la base utilizando productNew.id
            // Recorres caracteristicas y imagenes del producto recuperado y le haces un delete a casa una


            // a las caracterisdticas e imagenes almecenadas en las variables le seteas el producto obtenido anteriormente
            // y creas cada una de elllas

            Product updatedProduct = productServiceImpl.updateProduct(id, productNew);

            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Optional<Product> product = productServiceImpl.findProductById(id);
            return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productServiceImpl.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productServiceImpl.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}


