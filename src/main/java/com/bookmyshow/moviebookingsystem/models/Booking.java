package com.bookmyshow.moviebookingsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @ManyToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private Show show;
    @ManyToOne
    private User user;
    private Integer amount;
    @OneToMany
    private List<Payment> payments;

    private Date bookedAt;
}
