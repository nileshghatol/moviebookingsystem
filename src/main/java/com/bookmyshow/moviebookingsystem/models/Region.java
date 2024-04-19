package com.bookmyshow.moviebookingsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Region extends BaseModel {
    private String regionName;
    // 1:M
    // 1:1
    // 1:M
    @OneToMany
    private List<Theater> theaters;
}
