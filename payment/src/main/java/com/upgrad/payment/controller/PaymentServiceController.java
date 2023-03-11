package com.upgrad.payment.controller;

import com.upgrad.payment.DTO.GetTransactionDetailsDTO;
import com.upgrad.payment.DTO.TransactionRequestDTO;
import com.upgrad.payment.DTO.TransactionResponseDTO;
import com.upgrad.payment.entity.TransactionDetailsEntity;
import com.upgrad.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentServiceController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Method for generating the transaction ID
     */

    @PostMapping(value = "/transaction",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity generateTransactionID(@RequestBody TransactionRequestDTO transactionRequestDTO){

        TransactionDetailsEntity transactionDetailsEntity = new TransactionDetailsEntity();
        transactionDetailsEntity.setPaymentMode(transactionRequestDTO.getPaymentMode());
        transactionDetailsEntity.setBookingId(transactionRequestDTO.getBookingId());
        transactionDetailsEntity.setCardNumber(transactionRequestDTO.getCardNumber());
        transactionDetailsEntity.setUpiId(transactionRequestDTO.getUpiId());

        TransactionDetailsEntity createTransaction = transactionService.saveTransactionDetails(transactionDetailsEntity);

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        int transactionID = transactionResponseDTO.setTransactionID(transactionDetailsEntity.getId());

        return new ResponseEntity(transactionID, HttpStatus.CREATED);

    }

    @GetMapping(value = "/transaction/{transactionId}")
    public ResponseEntity getTransactionDetailsBasedOnId(@PathVariable(name = "transactionId") int transactionId){
        TransactionDetailsEntity transactionDetailsEntity = transactionService.getTransactionDetailsById(transactionId);
        GetTransactionDetailsDTO transactionDetailsDTO = new GetTransactionDetailsDTO();

        transactionDetailsDTO.setBookingId(transactionDetailsEntity.getBookingId());
        transactionDetailsDTO.setPaymentMode(transactionDetailsEntity.getPaymentMode());
        transactionDetailsDTO.setID(transactionDetailsEntity.getId());
        transactionDetailsDTO.setUpiId(transactionDetailsEntity.getUpiId());
        transactionDetailsDTO.setCardNumber(transactionDetailsEntity.getCardNumber());

        return new ResponseEntity(transactionDetailsDTO,HttpStatus.OK);

    }

}
