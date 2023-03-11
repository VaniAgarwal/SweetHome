package com.upgrad.SweetHome.service;

import com.upgrad.SweetHome.DTO.IdResponseDTO;
import com.upgrad.SweetHome.DTO.TransactionDTO;
import com.upgrad.SweetHome.Dao.BookingInfoDao;
import com.upgrad.SweetHome.Exception.InvalidModeOfPayment;
import com.upgrad.SweetHome.entity.BookingInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingInfoDao bookingInfoDao;

    @Autowired
    private RestTemplate restTemplate;

    private String transactionURL ="http://localhost:8081/payment/transaction";

    @Override
    public BookingInfoEntity saveBookingDetails(BookingInfoEntity bookingInfoEntity) {

        /**
         * get the number of days
         */
        try {
            int numberOfDays = (int) ChronoUnit.DAYS.between(bookingInfoEntity.getFromDate(), bookingInfoEntity.getToDate());


            /**
             * get room price
             */
            bookingInfoEntity.setRoomPrice(BookingService.totalRoomPrice(bookingInfoEntity.getNumOfRooms(),numberOfDays));

            /**
             * generating random room numbers
             */
            ArrayList<String> randomNumbers = new ArrayList<>();
            randomNumbers = BookingService.getRandomNumbers(bookingInfoEntity.getNumOfRooms());

            String getRandomNumbers = "";
            for (String s : randomNumbers) {
                getRandomNumbers += s + ",";
            }
            bookingInfoEntity.setRoomNumbers(getRandomNumbers);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return bookingInfoDao.save(bookingInfoEntity);
    }

    @Override
    public BookingInfoEntity getBookingDetails(int id) {
        return bookingInfoDao.findById(id).get();
    }

    @Override
    public IdResponseDTO getTransactionId(TransactionDTO transactionDTO) {
        IdResponseDTO receivedTransactionId = restTemplate.getForObject(transactionURL, IdResponseDTO.class);
        return receivedTransactionId;
        }





}
