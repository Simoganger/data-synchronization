package com.sprintgether.datasynchro.repository;

import com.sprintgether.datasynchro.model.central.CentralProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "centralTransactionManager")
public interface CentralProductRepository extends JpaRepository<CentralProduct, String> {

}
