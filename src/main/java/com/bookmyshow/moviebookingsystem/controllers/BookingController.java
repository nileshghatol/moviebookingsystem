package com.bookmyshow.moviebookingsystem.controllers;

import com.bookmyshow.moviebookingsystem.dto.BookMovieRequestDTO;
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
    public BookMovieRequestDTO bookMovie(BookMovieRequestDTO bookMovieRequestDTO) {
        return null;
    }
}
