package com.bookmyshow.moviebookingsystem.services;

import com.bookmyshow.moviebookingsystem.models.*;
import com.bookmyshow.moviebookingsystem.repositories.BookingRepository;
import com.bookmyshow.moviebookingsystem.repositories.ShowRepository;
import com.bookmyshow.moviebookingsystem.repositories.ShowSeatRepository;
import com.bookmyshow.moviebookingsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    @Autowired
    public BookingService(UserRepository userRepository,
                          BookingRepository bookingRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }
    // 3. -----Take a lock ---- (Start Transaction)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(
            Long userId,
            List<Long> seatIds,
            Long showId
    ) {
        try {
            // 1. Get the user by userId
            Optional<User> user = userRepository.findById(userId);
            // 2. Get the show by showId
            Optional<Show> show = showRepository.findById(showId);
            // 3. -----Take a lock ---- (Start Transaction)
            // 4. Get the showSeats from the showIds
            List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);
            // 5. check if all the seats are available
            for (ShowSeat showSeat : showSeats) {
                if (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) || showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED)) {
                    throw new RuntimeException("Selected Seats are not available. Try another selecting another Seats");
                }
            }
            // 6. if no, throw the error
            // 7. If available,change the status to blocked
            // 8. save it into database
            // 9. ---- release the lock --- end transaction
            // 10. save the bookings details into database
            // 11. return the booking object
            return null;
        } catch (Exception ex) {
            return null;
        }
    }


}
