package com.realtor.smart.service;

import com.realtor.smart.domain.User;

public interface WebTokenService {
    boolean validate(String token);
    String generateToken(User user);
}
