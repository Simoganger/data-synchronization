package com.sprintgether.datasynchro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SyncScheduler {

  private final SyncService syncService;

  @Scheduled(fixedDelay = 5 * 60 * 1000)
  public void runSync() {
    if(syncService.isInternetAvailable()) {
      log.info("Synchronizing data with central server ...");
      syncService.syncWithCentralDatabase();
      log.info("Data synchronized successfully!");
    }
  }

}
