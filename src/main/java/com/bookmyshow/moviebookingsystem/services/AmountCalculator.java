package com.bookmyshow.moviebookingsystem.services;

import com.bookmyshow.moviebookingsystem.models.Seat;
import com.bookmyshow.moviebookingsystem.models.Show;
import com.bookmyshow.moviebookingsystem.models.ShowSeat;
import com.bookmyshow.moviebookingsystem.models.ShowSeatType;
import com.bookmyshow.moviebookingsystem.repositories.SeatRepository;
import com.bookmyshow.moviebookingsystem.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmountCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    public AmountCalculator( ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public int calculate(List<ShowSeat> showSeats, Show show) {
        // 1. find out all the ShowSeatTypes using the show
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;

        // 2. get the seatTypes for all the selected seats
        for(ShowSeat showSeat : showSeats) {
            for (ShowSeatType seatType : showSeatTypes) {
                if (showSeat.getSeat().getSeatType().equals(seatType.getSeatType())) {
                    // 3. Calculate the amount and return
                    amount += seatType.getPrice();
                }
            }
        }

        return amount;
    }
}
