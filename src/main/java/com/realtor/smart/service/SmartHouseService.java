package com.realtor.smart.service;

import com.realtor.smart.domain.SmartHouse;
import com.realtor.smart.domain.User;

import java.util.List;

public interface SmartHouseService {
    List<SmartHouse> getHousesForUser(User user);
    void assignHousesToUser(User user, List<String> houses);
    void create(SmartHouse house);
    boolean checkExistingHouses(List<SmartHouse> houses);
}
