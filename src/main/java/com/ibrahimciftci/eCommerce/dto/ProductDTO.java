package com.ibrahimciftci.eCommerce.dto;

import com.ibrahimciftci.eCommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;


    public static ProductDTO fromEntity(Product product){
        return new ProductDTO(product.getId(), product.getCode(), product.getName(), product.getDescription(), product.getPrice());
    }

}