package com.realtor.smart.service.impl;

import com.realtor.smart.domain.SmartHouse;
import com.realtor.smart.domain.User;
import com.realtor.smart.repository.HouseRepository;
import com.realtor.smart.repository.UserHouseRepository;
import com.realtor.smart.service.SmartHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartHouseServiceImpl implements SmartHouseService {

    private final UserHouseRepository userHouseRepository;

    private final HouseRepository houseRepository;

    @Autowired
    public SmartHouseServiceImpl(UserHouseRepository userHouseRepository, HouseRepository houseRepository) {
        this.userHouseRepository = userHouseRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public List<SmartHouse> getHousesForUser(User user) {
        return userHouseRepository.getHousesForUser(user);
    }

    @Override
    public void assignHousesToUser(User user, List<String> names) {

        List<SmartHouse> houses = names.stream().map(houseRepository::getByHouseName).collect(Collectors.toList());

        userHouseRepository.assignHousesToUser(user, houses);
    }

    @Override
    public void create(SmartHouse house) {
        houseRepository.addHouse(house);
    }

    public boolean checkExistingHouses(List<SmartHouse> houses) {
        return houses.stream().allMatch(h -> houseRepository.existsHouse(h.getName()));
    }
}
