package com.booklify.service;

import com.booklify.domain.Transaction;
import com.booklify.domain.enums.TransactionStatus;
import com.booklify.repository.OrderRepository;
import com.booklify.repository.PaymentRepository;
import com.booklify.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private static ITransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus) {
        return transactionRepository.findByTransactionStatus(transactionStatus);
    }

    @Override
    public List<Transaction> findByOrder(Long orderId) {
        return transactionRepository.findByOrder_OrderId(orderId);
    }

    @Override
    public List<Transaction> findByPayment(Long paymentId) {
        return transactionRepository.findByPayment_PaymentId(paymentId);
    }

    @Override
    public List<Transaction> findByRegularUser(Long id) {
        return transactionRepository.findByRegularUser_Id(id);
    }



    @Override
    public Transaction save(Transaction entity) {
        return this.transactionRepository.save(entity);
    }

    @Override
    public Transaction findById(Long aLong) {
        return this.transactionRepository.findById(aLong).orElse(null);
    }

    @Override
    public Transaction update(Transaction entity) {
        return this.transactionRepository.save(entity);


    }

    @Override
    public boolean deleteById(Long aLong) {
        this.transactionRepository.deleteById(aLong);
        return true;
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
}
