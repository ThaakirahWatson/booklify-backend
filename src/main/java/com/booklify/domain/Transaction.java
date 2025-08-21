package com.booklify.domain;

import com.booklify.domain.enums.TransactionStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @CreationTimestamp
    @Column(name= "transaction_date", nullable = false, updatable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private Double transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Payment payment;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private RegularUser regularUser;

    //default constructor
    public Transaction() {
    }

    public Transaction(TransactionBuilder builder){
        this.transactionId = builder.transactionId;
        this.transactionDate = builder.transactionDate;
        this.transactionAmount = builder.transactionAmount;
        this.transactionStatus = builder.transactionStatus;
        this.order = builder.order;
        this.payment = builder.payment;
        this.regularUser = builder.regularUser;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionAmount=" + transactionAmount +
                ", transactionStatus=" + transactionStatus +
                ", order=" + order +
                ", payment=" + payment +
                ", regularUser=" + regularUser +
                '}';
    }

    public static class TransactionBuilder {
        private Long transactionId;
        private LocalDateTime transactionDate;
        private Double transactionAmount;
        private TransactionStatus transactionStatus;
        private Order order;
        private Payment payment;
        private RegularUser regularUser;

        public TransactionBuilder setTransactionId(Long transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public TransactionBuilder setTransactionDate(LocalDateTime transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public TransactionBuilder setTransactionAmount(Double transactionAmount) {
            this.transactionAmount = transactionAmount;
            return this;
        }

        public TransactionBuilder setTransactionStatus(TransactionStatus transactionStatus) {
            this.transactionStatus = transactionStatus;
            return this;
        }

        public TransactionBuilder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public TransactionBuilder setPayment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public TransactionBuilder setRegularUser(RegularUser regularUser) {
            this.regularUser = regularUser;
            return this;
        }

        //updates an object in repo
        public TransactionBuilder copy(Transaction transaction) {
            this.transactionId = transaction.transactionId;
            this.transactionDate = transaction.transactionDate;
            this.transactionAmount = transaction.transactionAmount;
            this.transactionStatus = transaction.transactionStatus;
            this.order = transaction.order;
            this.payment = transaction.payment;
            this.regularUser = transaction.regularUser;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
