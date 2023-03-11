package com.upgrad.payment.service;

import com.upgrad.payment.entity.TransactionDetailsEntity;

public interface TransactionService {
    public TransactionDetailsEntity saveTransactionDetails(TransactionDetailsEntity transactionDetailsEntity);

    public TransactionDetailsEntity getTransactionDetailsById(int transactionId);

}
