package com.realtor.smart.repository;

import com.realtor.smart.domain.SmartHouse;
import com.realtor.smart.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  Храним дома, назначенные пользователям

@Repository
public class UserHouseRepository {

    private Map<User, List<SmartHouse>> map;

    public UserHouseRepository() {
        map = new HashMap<>();
    }

    public void assignHousesToUser(User user, List<SmartHouse> houses) {
        map.put(user, houses);
    }

    public List<SmartHouse> getHousesForUser(User user) {
        return map.get(user);
    }


}
