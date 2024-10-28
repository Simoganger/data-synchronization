package com.sprintgether.datasynchro.service;

import com.sprintgether.datasynchro.model.central.CentralProduct;
import com.sprintgether.datasynchro.model.local.Product;
import com.sprintgether.datasynchro.repository.CentralProductRepository;
import com.sprintgether.datasynchro.repository.LocalProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final LocalProductRepository localProductRepository;
  private final CentralProductRepository centralProductRepository;

  public void saveLocalProduct() {
    Product product = Product.builder()
        .name("Sugar")
        .description("White sugar")
        .build();
    localProductRepository.save(product);
  }

  public void saveCentralProduct() {
    CentralProduct centralProduct = CentralProduct.builder()
        .name("Coffee")
        .description("Black and bitter coffee")
        .build();
    centralProductRepository.save(centralProduct);
  }

}
