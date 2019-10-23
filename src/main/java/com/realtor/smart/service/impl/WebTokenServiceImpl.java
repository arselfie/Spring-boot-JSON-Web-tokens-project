package com.realtor.smart.service.impl;

import com.realtor.smart.domain.User;
import com.realtor.smart.repository.HouseRepository;
import com.realtor.smart.repository.UserRepository;
import com.realtor.smart.service.WebTokenService;
import com.realtor.smart.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebTokenServiceImpl implements WebTokenService {

    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Autowired
    public WebTokenServiceImpl(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(String token) {

        Claims claims = jwtTokenUtil.getClaimsFromToken(token);

        String name = claims.get(USER_NAME, String.class);
        String password = claims.get(USER_PASSWORD, String.class);

        return userRepository.isUserWithNameAndPassword(new User(name, password));
    }

    @Override
    public String generateToken(User user) {
        return jwtTokenUtil.generateToken(user);
    }
}
