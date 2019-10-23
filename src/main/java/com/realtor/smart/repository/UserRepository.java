package com.realtor.smart.repository;

import com.realtor.smart.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {
    private Set<User> users;

    public UserRepository() {
        users = new HashSet<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean isUserWithNameAndPassword(User user) {
        return users.contains(user);
    }

}
