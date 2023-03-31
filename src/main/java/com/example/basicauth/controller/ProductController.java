package com.example.basicauth.controller;

import com.example.basicauth.model.Product;
import com.example.basicauth.model.UserInfo;
import com.example.basicauth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/welcome")
    public String productMessage() {
        return "<h1>Hoş Geldiniz</h1>";
    }
    @PostMapping("/new")
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo) {
        return new ResponseEntity<>(productService.createUserInfo(userInfo), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllPost());
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }


}
