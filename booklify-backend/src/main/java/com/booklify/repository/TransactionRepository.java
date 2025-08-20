package com.booklify.repository;

import com.booklify.domain.RegularUser;
import com.booklify.domain.Transaction;
import com.booklify.domain.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);
    List<Transaction> findByOrder_OrderId(Long orderId);
    List<Transaction> findByPayment_Id(Long id);
    List<Transaction> findByRegularUser_Id(Long id);

}
