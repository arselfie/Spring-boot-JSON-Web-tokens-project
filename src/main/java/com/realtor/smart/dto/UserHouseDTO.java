package com.realtor.smart.dto;

import com.realtor.smart.domain.SmartHouse;
import com.realtor.smart.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Используем equals и хэшкод потому что дома и юзеры храним в коллекции Set

public class UserHouseDTO {
    private User user;
    private List<SmartHouse> houses;

    public UserHouseDTO() {
        houses = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SmartHouse> getHouses() {
        return houses;
    }

    public void setHouses(List<SmartHouse> houses) {
        this.houses = houses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHouseDTO userHouseDTO = (UserHouseDTO) o;
        return Objects.equals(user, userHouseDTO.user) &&
                Objects.equals(houses, userHouseDTO.houses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, houses);
    }
}
