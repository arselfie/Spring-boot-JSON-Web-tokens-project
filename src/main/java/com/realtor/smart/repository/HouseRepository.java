package com.realtor.smart.repository;

import com.realtor.smart.domain.SmartHouse;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

// Хранит дома

@Repository
public class HouseRepository {

    private Set<SmartHouse> houses;

    public HouseRepository() {
        this.houses = new HashSet<>();
    }

    public void addHouse(SmartHouse house) {
        houses.add(house);
    }

    public SmartHouse getByHouseName(String houseName) {
        return houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().get();
    }

    public boolean existsHouse(String houseName) {
        return houses.stream().anyMatch(h -> h.getName().equals(houseName));
    }
}
