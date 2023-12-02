package com.ibrahimciftci.eCommerce.controller;

import com.ibrahimciftci.eCommerce.dto.ProductDTO;
import com.ibrahimciftci.eCommerce.model.Product;
import com.ibrahimciftci.eCommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/productList")
    public ResponseEntity<List<Product>> productList() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));

    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id,productDTO));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
