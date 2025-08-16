package com.booklify.factory;

import com.booklify.domain.Order;
import com.booklify.domain.Payment;
import com.booklify.domain.RegularUser;
import com.booklify.domain.Transaction;
import com.booklify.domain.enums.TransactionStatus;
import com.booklify.util.Helper;

import java.time.LocalDateTime;

public class TransactionFactory {
    public static Transaction createTransaction(Long transactionId,
                                                LocalDateTime transactionDate,
                                                double transactionAmount,
                                                TransactionStatus transactionStatus,
                                                Order order, Payment payment, RegularUser regularUser) {

        if(transactionId == null || !Helper.isValidDateTime(transactionDate) || !Helper.isValidAmount(transactionAmount)
        || transactionStatus == null || order == null || payment == null || regularUser == null) {
            return null;
        }

        return new Transaction.TransactionBuilder()
                .setTransactionId(transactionId)
                .setTransactionDate(transactionDate)
                .setTransactionAmount(transactionAmount)
                .setTransactionStatus(transactionStatus)
                .setOrder(order)
                .setPayment(payment)
                .setRegularUser(regularUser)
                .build();
    }
}
