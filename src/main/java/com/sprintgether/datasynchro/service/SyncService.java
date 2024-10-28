package com.sprintgether.datasynchro.service;

import com.sprintgether.datasynchro.model.central.CentralProduct;
import com.sprintgether.datasynchro.model.local.Product;
import com.sprintgether.datasynchro.repository.central.CentralProductRepository;
import com.sprintgether.datasynchro.repository.local.LocalProductRepository;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncService {

  private final LocalProductRepository localProductRepository;
  private final CentralProductRepository centralProductRepository;

  public void syncWithCentralDatabase() {
    LocalDateTime lastSyncTime = getLastSyncTime();
    List<Product> localProducts = localProductRepository.findAllByLastModifiedAfter(lastSyncTime);

    localProducts.forEach(localProduct -> {
      Optional<CentralProduct> centralProductOptional
          = centralProductRepository.findById(localProduct.getId());
      if (centralProductOptional.isPresent()) {
        CentralProduct centralProduct = centralProductOptional.get();
        centralProduct.setName(localProduct.getName());
        centralProduct.setDescription(localProduct.getDescription());
        centralProduct.setQuantity(localProduct.getQuantity());
        centralProductRepository.save(centralProduct);
      } else {
        CentralProduct centralProduct = CentralProduct.builder()
            .id(localProduct.getId())
            .name(localProduct.getName())
            .description(localProduct.getDescription())
            .price(localProduct.getPrice())
            .quantity(localProduct.getQuantity())
            .build();
        centralProductRepository.save(centralProduct);
      }
    });
    updateLastSyncTime(LocalDateTime.now());
  }

  public boolean isInternetAvailable() {
    try {
      URL url = new URL("http://www.google.com"); // TODO can use custom url here
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      urlConnection.setRequestMethod("HEAD");
      urlConnection.setConnectTimeout(3000);
      urlConnection.connect();
      return (urlConnection.getResponseCode() == 200);
    } catch(IOException e) {
      return false;
    }
  }

  public void updateLastSyncTime(LocalDateTime now) {
    // TODO: update the table that contains last sync time with now
  }

  public LocalDateTime getLastSyncTime() {
    // TODO retrieve last sync time from a specify database table
    return LocalDateTime.now().minusMinutes(30);
  }
}
