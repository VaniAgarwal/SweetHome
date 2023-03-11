package com.upgrad.payment.Dao;

import com.upgrad.payment.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<TransactionDetailsEntity,Integer> {


}
