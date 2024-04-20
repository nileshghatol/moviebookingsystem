package com.bookmyshow.moviebookingsystem.repositories;

import com.bookmyshow.moviebookingsystem.models.Show;
import com.bookmyshow.moviebookingsystem.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);
}
