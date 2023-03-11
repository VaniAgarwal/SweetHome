package com.upgrad.SweetHome.controller;

import com.upgrad.SweetHome.DTO.BookingInfoRequestDTO;
import com.upgrad.SweetHome.DTO.BookingInfoResponseDTO;
import com.upgrad.SweetHome.DTO.IdResponseDTO;
import com.upgrad.SweetHome.DTO.TransactionDTO;
import com.upgrad.SweetHome.Exception.InvalidModeOfPayment;
import com.upgrad.SweetHome.entity.BookingInfoEntity;
import com.upgrad.SweetHome.entity.TransactionDetailsEntity;
import com.upgrad.SweetHome.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hotel")
public class BookingServiceController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;


    /**
     * Method for creating a booking
     * 127.0.0.1:8080/hotel/booking
     */
    @PostMapping(value = "/booking",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBooking(@RequestBody BookingInfoRequestDTO bookingInfoRequestDTO){

        //convert bookingInfoDTO to bookingInfoEntity we will make use of model mapper

        BookingInfoEntity newBooking = modelMapper.map(bookingInfoRequestDTO,BookingInfoEntity.class);
        BookingInfoEntity createBooking = bookingService.saveBookingDetails(newBooking);

        BookingInfoResponseDTO savedBooking = modelMapper.map(createBooking, BookingInfoResponseDTO.class);
        return new ResponseEntity(savedBooking, HttpStatus.CREATED);

    }

    @GetMapping(value = "/booking/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDetailsOfBooking(@PathVariable(name = "id") int id){
        BookingInfoEntity responseBooking = bookingService.getBookingDetails(id);

        BookingInfoResponseDTO responseBookingDTO = modelMapper.map(responseBooking, BookingInfoResponseDTO.class);
        return new ResponseEntity(responseBookingDTO,HttpStatus.OK);

    }
    @PostMapping(value = "/booking/{id}/transaction",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTransactionId(@PathVariable(name = "id") int id,@RequestBody TransactionDTO transactionDTO) {
        if (transactionDTO.getPaymentMode().equals("UPI") || transactionDTO.getPaymentMode().equals("CARD")) {

            IdResponseDTO createTransactionId = bookingService.getTransactionId(transactionDTO);
            BookingInfoEntity updateTransactionId = new BookingInfoEntity();
            BookingInfoResponseDTO bookingInfoResponseDTO = modelMapper.map(updateTransactionId,BookingInfoResponseDTO.class);
            bookingInfoResponseDTO.setTransactionId(createTransactionId.getTransactionID());

            return new ResponseEntity(bookingInfoResponseDTO, HttpStatus.OK);
        }
        else {
            InvalidModeOfPayment invalidModeOfPayment = new InvalidModeOfPayment();
            invalidModeOfPayment.setMessage("invalid mode of payment");
            invalidModeOfPayment.setStatusCode(400);
            return new ResponseEntity(invalidModeOfPayment,HttpStatus.BAD_REQUEST);
        }
    }





}
