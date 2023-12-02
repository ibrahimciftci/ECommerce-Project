package com.ibrahimciftci.eCommerce.dto;

import com.ibrahimciftci.eCommerce.model.Product;

import java.math.BigDecimal;


public record ProductDTO(
        String code,
        String name,
        String description,
        BigDecimal price
) {

    public static ProductDTO fromModel(Product product){
        return new ProductDTO(product.getCode(), product.getName(), product.getDescription(), product.getPrice());
    }
}