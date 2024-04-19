package com.bookmyshow.moviebookingsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Theater extends BaseModel {
    private String theaterName;
    @ManyToOne
    private Region region;
    @OneToMany
    private List<Screen> screens;
}
