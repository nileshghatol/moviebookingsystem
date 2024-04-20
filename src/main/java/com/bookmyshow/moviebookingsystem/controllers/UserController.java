package com.bookmyshow.moviebookingsystem.controllers;

import com.bookmyshow.moviebookingsystem.dto.ResponseStatus;
import com.bookmyshow.moviebookingsystem.dto.SignUpRequestDTO;
import com.bookmyshow.moviebookingsystem.dto.SignUpResponseDTO;
import com.bookmyshow.moviebookingsystem.models.User;
import com.bookmyshow.moviebookingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        try {
            User user = userService.signUp(
                    signUpRequestDTO.getEmail(),
                    signUpRequestDTO.getPassword()
            );

            signUpResponseDTO.setUserId(user.getId());
            signUpResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            return signUpResponseDTO;
        } catch (Exception e) {
            signUpResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return signUpResponseDTO;
    }

    public SignUpResponseDTO userLogin(SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();
        try {
            User user = userService.userLogin(signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword());
            signUpResponseDTO.setUserId(user.getId());
            signUpResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception exception) {
            signUpResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return signUpResponseDTO;
    }

}
