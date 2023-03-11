package com.upgrad.SweetHome.DTO;

public class BookingDTO {
    private  BookingInfoRequestDTO bookingInfoRequestDTO;
    private TransactionDTO transactionDTO;

    private BookingInfoResponseDTO bookingInfoResponseDTO;

    public BookingInfoRequestDTO getBookingInfoRequestDTO() {
        return bookingInfoRequestDTO;
    }

    public void setBookingInfoRequestDTO(BookingInfoRequestDTO bookingInfoRequestDTO) {
        this.bookingInfoRequestDTO = bookingInfoRequestDTO;
    }

    public TransactionDTO getTransactionDTO() {
        return transactionDTO;
    }

    public void setTransactionDTO(TransactionDTO transactionDTO) {
        this.transactionDTO = transactionDTO;
    }

    public BookingInfoResponseDTO getBookingInfoResponseDTO() {
        return bookingInfoResponseDTO;
    }

    public void setBookingInfoResponseDTO(BookingInfoResponseDTO bookingInfoResponseDTO) {
        this.bookingInfoResponseDTO = bookingInfoResponseDTO;
    }
}
