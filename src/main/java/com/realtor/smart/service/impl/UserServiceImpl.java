package com.realtor.smart.service.impl;

import com.realtor.smart.domain.SmartHouse;
import com.realtor.smart.domain.User;
import com.realtor.smart.dto.UserHouseDTO;
import com.realtor.smart.repository.UserHouseRepository;
import com.realtor.smart.repository.UserRepository;
import com.realtor.smart.service.UserService;
import com.realtor.smart.service.WebTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserHouseRepository houseRepository;

    private final WebTokenService tokenService;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserHouseRepository houseRepository, WebTokenService tokenService, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(User user) {

        boolean isUser = false;

        if (!userRepository.isUserWithNameAndPassword(user)) {
            userRepository.addUser(user);
            isUser = true;
        }
        return isUser;
    }

    @Override
    public boolean existsUserAndToken(User user, String token) {
        return userRepository.isUserWithNameAndPassword(user) && tokenService.validate(token);
    }

    @Override
    public boolean existsUser(User user) {
        return userRepository.isUserWithNameAndPassword(user);
    }

}
