package com.example.retrospect.user.controller;

import com.example.retrospect.user.dto.LoginDTO;
import com.example.retrospect.user.dto.ResetPasswordDTO;
import com.example.retrospect.user.dto.SignUpDTO;
import com.example.retrospect.user.dto.UpdateUserDTO;
import com.example.retrospect.user.entity.UserEntity;
import com.example.retrospect.user.repository.IUserRepository;
import com.example.retrospect.user.service.IUserService;
import com.example.retrospect.user.service.UserService;
import com.example.retrospect.util.UserJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@ResponseStatus(HttpStatus.OK)
@CrossOrigin(allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    UserJWT userJWT;
    @PostMapping("/signup")
    public String userSignup(@RequestBody SignUpDTO signUpDTO) {
        return service.userSignup(signUpDTO);

    }
    @PostMapping("/login")
    public String UserLogin(@RequestBody LoginDTO loginDTO) {
        return service.Userlogin(loginDTO);

    }
    @GetMapping("/getbyJWT")
    public Optional<UserEntity> getUserByJWT(@RequestHeader String token) {
        return service.getUserByJWT(token);
    }

    @PutMapping("/update/{id}")
    public UserEntity updateUser (@PathVariable int id, @RequestBody UpdateUserDTO userEntity) {
        return service.updateUser(id,userEntity);
    }

    @PostMapping("/resetpassword")
    public String resetPassword (@RequestBody ResetPasswordDTO resetPassword) {
        return service.resetPassword(resetPassword.getUserEmail(), resetPassword.getOldPassword(), resetPassword.getNewPassword());

    }
}
