package com.bookmyshow.moviebookingsystem.repositories;

import com.bookmyshow.moviebookingsystem.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
