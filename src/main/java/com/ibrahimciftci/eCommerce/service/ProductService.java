package com.ibrahimciftci.eCommerce.service;

import com.ibrahimciftci.eCommerce.dto.ProductDTO;
import com.ibrahimciftci.eCommerce.model.Product;
import com.ibrahimciftci.eCommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public Product createProduct(ProductDTO productDTO) {
        Product newProduct = Product.builder()
                .code(productDTO.code())
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .build();

        return productRepository.save(newProduct);
    }

    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = getProductById(productId);
        existingProduct.setCode(productDTO.code());
        existingProduct.setName(productDTO.name());
        existingProduct.setDescription(productDTO.description());
        existingProduct.setPrice(productDTO.price());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> searchProductsByName(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    // Ürünün stok durumunu güncelleme metodu
    public void updateStock(Long productId, int newStock) {
        Product product = getProductById(productId);
        product.setStock(newStock);
        productRepository.save(product);
    }

    // Ürünü indirimli olarak güncelleme metodu
    public void updateDiscount(Long productId, BigDecimal discountRate) {
        Product product = getProductById(productId);
        BigDecimal discountedPrice = product.getPrice().multiply(BigDecimal.ONE.subtract(discountRate));
        product.setDiscountedPrice(discountedPrice);
        productRepository.save(product);
    }

// Diğer özel ürün işlemleri metotları...
}