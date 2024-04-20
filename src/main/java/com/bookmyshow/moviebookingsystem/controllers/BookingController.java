package com.bookmyshow.moviebookingsystem.controllers;

import com.bookmyshow.moviebookingsystem.dto.BookMovieRequestDTO;
import com.bookmyshow.moviebookingsystem.dto.BookMovieResponseDTO;
import com.bookmyshow.moviebookingsystem.dto.ResponseStatus;
import com.bookmyshow.moviebookingsystem.models.Booking;
import com.bookmyshow.moviebookingsystem.models.BookingStatus;
import com.bookmyshow.moviebookingsystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public BookMovieResponseDTO bookMovie(BookMovieRequestDTO bookMovieRequestDTO) {
        BookMovieResponseDTO bookMovieResponseDTO = new BookMovieResponseDTO();
        try {
                Booking booking = bookingService.bookMovie(
                        bookMovieRequestDTO.getUserId(),
                        bookMovieRequestDTO.getShowSeatsIds(),
                        bookMovieRequestDTO.getShowId() );

            bookMovieResponseDTO.setBookingId(booking.getId());
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            bookMovieResponseDTO.setAmount(booking.getAmount());


        } catch (Exception ex) {
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return null;
    }
}
