package com.grupo3.digitalbook.demo.service;

import com.grupo3.digitalbook.demo.entity.ProductType;
import com.grupo3.digitalbook.demo.exception.ResourceNotFoundException;

import java.util.List;

public interface IProductTypeService {
    ProductType createProductType(ProductType productType);

    ProductType updateProductType(Long productTypeId, ProductType productType) throws ResourceNotFoundException;

    void deleteProductType(Long id);

    ProductType findProductTypeById(Long id) throws ResourceNotFoundException;

    List<ProductType> getAllProductTypes();
}
