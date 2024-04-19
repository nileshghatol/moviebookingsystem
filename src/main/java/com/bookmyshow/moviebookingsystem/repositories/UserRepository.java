package com.bookmyshow.moviebookingsystem.repositories;

import com.bookmyshow.moviebookingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);
}
