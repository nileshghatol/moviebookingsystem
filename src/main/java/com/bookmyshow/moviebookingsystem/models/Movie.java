package com.bookmyshow.moviebookingsystem.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String name;
    private Date releasedAt;
}
