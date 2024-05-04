package com.example.retrospect.user.service;

import com.example.retrospect.user.dto.LoginDTO;
import com.example.retrospect.user.dto.SignUpDTO;
import com.example.retrospect.user.dto.UpdateUserDTO;
import com.example.retrospect.user.entity.UserEntity;
import com.example.retrospect.user.repository.IUserRepository;
import com.example.retrospect.util.UserJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService implements IUserService {
  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    IUserRepository iUserRepository;


    @Autowired
    UserJWT userJWT;

    @Override
    public String userSignup(SignUpDTO signUpDTO) {
        UserEntity userEntity = new UserEntity();
        String encryptedPassword=bCryptPasswordEncoder.encode(signUpDTO.getUserPassword());
        signUpDTO.setUserPassword((encryptedPassword));

        userEntity.setUserEmail(signUpDTO.getUserEmail());
        userEntity.setUserName(signUpDTO.getUserName());
        userEntity.setUserPassword(signUpDTO.getUserPassword());
        userEntity.setUserRole(signUpDTO.getUserRole());
        iUserRepository.save(userEntity);

        return  "You have been signed up successfully";

    }

    @Override
    public Optional<UserEntity> getUserByJWT(String token) {
        int userId = userJWT.decodeToken(token);
        System.out.println(" service" + userId);
        return iUserRepository.findById(userId);
    }


    @Override
    public String Userlogin(LoginDTO loginDTO) {
        UserEntity userEntity = iUserRepository.findByEmailId(loginDTO.getUserEmail());
        if(userEntity != null && bCryptPasswordEncoder.matches(loginDTO.getUserPassword(), userEntity.getUserPassword())) {
            String token = userJWT.generateToken(userEntity.getUserId());
            return token;
        }
        return "login failed";
    }

    @Override
    public UserEntity updateUser(int id, UpdateUserDTO userEntity){
        Optional<UserEntity> userOptional = iUserRepository.findById(id);
        if(userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Update only username and email
            user.setUserName(userEntity.getUserName());
            user.setUserEmail(userEntity.getUserEmail());

            // Save and return the updated user
            return iUserRepository.save(user);
        } else {
            // Handle the case when user is not found
            return null; // or throw an exception
        }
    }
}
