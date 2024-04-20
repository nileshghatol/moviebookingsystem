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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private AmountCalculator amountCalculator;
    @Autowired
    public BookingService(UserRepository userRepository,
                          BookingRepository bookingRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          AmountCalculator amountCalculator) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.amountCalculator = amountCalculator;
    }
    // 3. -----Take a lock ---- (Start Transaction)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(
            Long userId,
            List<Long> showSeatsIds,
            Long showId
    ) {
        try {
            // 1. Get the user by userId
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                throw new RuntimeException("No such User exist");
            }
            User bookedBy = userOptional.get();

            // 2. Get the show by showId
            Optional<Show> showOptional = showRepository.findById(showId);
            if (showOptional.isEmpty()) {
                throw new RuntimeException("No such Show exist");
            }
            Show bookedShow = showOptional.get();

            // 3. -----Take a lock ---- (Start Transaction)
            // 4. Get the showSeats from the showIds
            List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatsIds);
            // 5. check if all the seats are available
            for (ShowSeat showSeat : showSeats) {
                // 6. if no, throw the error
                LocalDateTime localDateTime;

                if (( showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED) ||
                        showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) && Duration.between(showSeat.getLockedAt().toInstant(), LocalDateTime.now()).toMinutes() < 15)) {
                    throw new RuntimeException("Selected Seats are not available. Try selecting another Seats");
                }
            }

            List<ShowSeat> blockedShowSeats = new ArrayList<>();
                // 7. If available,change the status to blocked
            for (ShowSeat showSeat : showSeats) {
                showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
                showSeat.setLockedAt(new Date());
                // 8. save it into database
                blockedShowSeats.add(showSeatRepository.save(showSeat));
            }
            // 9. ---- release the lock --- end transaction
            // 10. save the bookings details into database & return the booking object
            Booking booking = new Booking();
            booking.setBookedAt(new Date());
            booking.setUser(bookedBy);
            booking.setShowSeats(blockedShowSeats);
            booking.setShow(bookedShow);
            booking.setBookingStatus(BookingStatus.Pending);
            booking.setAmount(amountCalculator.calculate(showSeats, bookedShow));
            booking.setPayments(new ArrayList<>());

            return bookingRepository.save(booking);

        } catch (Exception ex) {
            return null;
        }
    }


}
