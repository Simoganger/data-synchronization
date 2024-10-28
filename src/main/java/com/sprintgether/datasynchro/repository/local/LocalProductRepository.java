package com.sprintgether.datasynchro.repository;

import com.sprintgether.datasynchro.model.local.Product;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "localTransactionManager")
public interface LocalProductRepository extends JpaRepository<Product, String> {

  List<Product> findAllByLastModifiedAfter(@Param("lastSyncTime") LocalDateTime lastSyncTime);
}
