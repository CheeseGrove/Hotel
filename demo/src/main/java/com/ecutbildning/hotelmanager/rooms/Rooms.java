package com.ecutbildning.hotelmanager.rooms;

import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Rooms {

    Boolean booked;
    ArrayList<String> orderedFood = new ArrayList<>();

    public ArrayList<String> getOrderedFood() {
        return orderedFood;
    }

    public void setOrderedFood(ArrayList<String> orderedFood) {
        this.orderedFood = orderedFood;
    }

    public boolean getBooked(){
        return booked;
    }

    public void setBooked(){
        this.booked = booked;
    }


    abstract int getNumberOfBeds();
    abstract boolean getAC();
    abstract boolean getBreakfast();
    abstract double getChargePerDay();

}
