package com.bookmyshow.moviebookingsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookMovieRequestDTO {
    private List<Long> showSeatsIds;
    private Long showId;
    private Long userId;

}
