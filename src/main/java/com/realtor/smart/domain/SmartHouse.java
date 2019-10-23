package com.realtor.smart.domain;

import java.util.Objects;

public class SmartHouse {

    private String name;
    private int numberRooms;

    public SmartHouse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberRooms() {
        return numberRooms;
    }

    public void setNumberRooms(int numberRooms) {
        this.numberRooms = numberRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartHouse that = (SmartHouse) o;
        return numberRooms == that.numberRooms &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberRooms);
    }
}
