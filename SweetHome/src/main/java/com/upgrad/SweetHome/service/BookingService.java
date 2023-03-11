package com.upgrad.SweetHome.service;

import com.upgrad.SweetHome.DTO.IdResponseDTO;
import com.upgrad.SweetHome.DTO.TransactionDTO;
import com.upgrad.SweetHome.entity.BookingInfoEntity;

import java.util.ArrayList;
import java.util.Random;

public interface BookingService {

    public BookingInfoEntity saveBookingDetails(BookingInfoEntity bookingInfoEntity);
    public BookingInfoEntity getBookingDetails(int id);

    public static int totalRoomPrice(int number_of_rooms,int number_of_days){
        int roomPrice = 1000* number_of_rooms*(number_of_days);
        return roomPrice;
    }
    public  static ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

    public IdResponseDTO getTransactionId(TransactionDTO transactionDTO);









}
