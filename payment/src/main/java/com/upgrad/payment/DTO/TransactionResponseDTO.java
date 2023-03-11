package com.upgrad.payment.DTO;

public class TransactionResponseDTO {
    private int TransactionID;

    public int getTransactionID() {
        return TransactionID;
    }

    public int setTransactionID(int transactionID) {
        this.TransactionID = transactionID;
        return transactionID;
    }
}
