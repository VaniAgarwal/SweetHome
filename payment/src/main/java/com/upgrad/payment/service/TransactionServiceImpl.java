package com.upgrad.payment.service;

import com.upgrad.payment.Dao.TransactionDao;
import com.upgrad.payment.entity.TransactionDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;
    @Override
    public TransactionDetailsEntity saveTransactionDetails(TransactionDetailsEntity transactionDetailsEntity) {
        return transactionDao.save(transactionDetailsEntity);

        }

    @Override
    public TransactionDetailsEntity getTransactionDetailsById(int transactionId) {
        return transactionDao.findById(transactionId).get();
    }


}
