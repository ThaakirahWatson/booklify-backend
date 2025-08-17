package com.booklify.service;

import com.booklify.domain.Transaction;
import com.booklify.domain.enums.TransactionStatus;

import java.util.List;

public interface ITransactionService extends IService<Transaction, Long>{

    List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);
    List<Transaction> findByOrder(Long orderId);
    List<Transaction> findByPayment(Long paymentId);
    List<Transaction> findByRegularUser(Long id);
    List<Transaction> getAll();
}
