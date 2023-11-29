package com.ibrahimciftci.eCommerce.service;

import com.ibrahimciftci.eCommerce.dto.ProductDTO;
import com.ibrahimciftci.eCommerce.entity.Product;
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
        Product product = new Product();
        product.setCode(productDTO.getCode());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = getProductById(productId);
        existingProduct.setCode(productDTO.getCode());
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }

   // public List<Product> getProductsByCategory(String category) {
   //     return productRepository.findByCategory(category);
    //}

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