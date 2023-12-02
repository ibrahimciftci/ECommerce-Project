package com.ibrahimciftci.eCommerce.model;

import com.ibrahimciftci.eCommerce.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;
    private BigDecimal price;


    public static Product fromDto(ProductDTO productDTO){
        Product product = new Product();
        product.setCode(productDTO.code());
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());
        return product;
    }

    public void setStock(int newStock) {
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
    }
}