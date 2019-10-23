package com.realtor.smart.service;

import com.realtor.smart.domain.User;

public interface UserService {
    boolean register(User user);
    boolean existsUserAndToken(User user, String token);
    boolean existsUser(User user);
}
