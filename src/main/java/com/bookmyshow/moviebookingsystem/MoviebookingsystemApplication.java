package com.bookmyshow.moviebookingsystem;

import com.bookmyshow.moviebookingsystem.controllers.UserController;
import com.bookmyshow.moviebookingsystem.dto.ResponseStatus;
import com.bookmyshow.moviebookingsystem.dto.SignUpRequestDTO;
import com.bookmyshow.moviebookingsystem.dto.SignUpResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MoviebookingsystemApplication implements CommandLineRunner {
    @Autowired
    private UserController userController;
    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setEmail("nilesh12ghatol@sap.com");
        signUpRequestDTO.setPassword("1223234");
//        userController.signUp(signUpRequestDTO);
       SignUpResponseDTO signUpResponseDTO = userController.userLogin(signUpRequestDTO);
       if (signUpResponseDTO.getResponseStatus().equals(ResponseStatus.SUCCESS)){
           System.out.println(signUpResponseDTO.getUserId());
       }

    }

    public static void main(String[] args) {
        SpringApplication.run(MoviebookingsystemApplication.class, args);
    }

}
