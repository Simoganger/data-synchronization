package com.sprintgether.datasynchro.service;

import com.sprintgether.datasynchro.model.central.CentralProduct;
import com.sprintgether.datasynchro.model.local.Product;
import com.sprintgether.datasynchro.repository.central.CentralProductRepository;
import com.sprintgether.datasynchro.repository.local.LocalProductRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final LocalProductRepository localProductRepository;
  private final CentralProductRepository centralProductRepository;

  public Product saveLocalProduct(Product product) {
    product.setLastModified(LocalDateTime.now());
    return localProductRepository.save(product);
  }

  public CentralProduct saveCentralProduct(CentralProduct centralProduct) {
    centralProduct.setLastModified(LocalDateTime.now());
    return centralProductRepository.save(centralProduct);
  }

}
