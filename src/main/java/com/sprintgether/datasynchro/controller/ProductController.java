package com.sprintgether.datasynchro.controller;

import com.sprintgether.datasynchro.model.ResponseMessage;
import com.sprintgether.datasynchro.model.local.Product;
import com.sprintgether.datasynchro.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ResponseMessage> createLocalProduct(@RequestBody Product product) {
    Product savedProduct = productService.saveLocalProduct(product);
    ResponseMessage responseMessage = ResponseMessage.builder()
        .message("The local product is saved successfully!")
        .code(200)
        .data(savedProduct)
        .build();
    return ResponseEntity.ok(responseMessage);
  }
}
