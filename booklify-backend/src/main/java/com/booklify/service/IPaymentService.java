package com.booklify.service;

import com.booklify.domain.Payment;
import com.booklify.domain.enums.PaymentStatus;
import java.util.List;

public interface IPaymentService extends IService<Payment, Long> {
    //Lungela Nkunkuma 222491582
    List<Payment> getAll();
    List<Payment> findByUser(Long userId);
    List<Payment> findByStatus(PaymentStatus status);
    boolean processRefund(Long paymentId, double amount);
}