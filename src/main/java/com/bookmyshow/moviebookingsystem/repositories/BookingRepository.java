package com.bookmyshow.moviebookingsystem.repositories;

import com.bookmyshow.moviebookingsystem.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
