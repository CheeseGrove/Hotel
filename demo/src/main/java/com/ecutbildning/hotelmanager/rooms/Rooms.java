package com.ecutbildning.hotelmanager.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Rooms{

    @Id
    public String id;

    boolean booked =false;
    boolean hasAC = false;
    boolean hasFreeBreakfest = false;
    int numberOfBeds, daysBooked, chargePerDay;
    ArrayList<String> orderedFood = new ArrayList<>();
    String typeOfRoom;


    public Rooms(String typeOfRoom) {

        this.typeOfRoom = typeOfRoom;
        switch(typeOfRoom.toLowerCase()) {
            case "poorsingle":
                this.setProperties(false, false, false, 2, 0, 150, "Poor Single Room");
                break;
            case "poordouble":
                this.setProperties(false, false, false, 2, 0, 250, "Poor Double Room");
                break;
            case "luxurysingle":
                this.setProperties(false, true, true, 1, 0, 1300, "Luxury Single Room");
                break;
            case "luxurydouble":
                this.setProperties(false, false, false, 4, 0, 2500, "Luxury Double Room");
                break;
        }
    }

    public void setProperties (boolean booked, boolean hasAC, boolean hasFreeBreakfest, int numberOfBeds, int daysBooked, int chargePerDay, String typeOfRoom) {
        this.booked = booked;
        this.hasAC = hasAC;
        this.hasFreeBreakfest = hasFreeBreakfest;
        this.numberOfBeds = numberOfBeds;
        this.daysBooked = daysBooked;
        this.chargePerDay = chargePerDay;
        this.typeOfRoom = typeOfRoom;
        this.id = this.id;
    }

    public ArrayList<String> getOrderedFood() {
        return orderedFood;
    }

    public void setOrderedFood(ArrayList<String> orderedFood) {
        this.orderedFood = orderedFood;
    }

    public boolean getBooked(){
        return booked;
    }

    public void setBooked(boolean booked){
        this.booked = booked;
    }

    public Boolean getHasAC() {
        return hasAC;
    }

    public Boolean getHasFreeBreakfest() {
        return hasFreeBreakfest;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public int getDaysBooked() {
        return daysBooked;
    }

    public int getChargePerDay() {
        return chargePerDay;
    }

    public String getTypeOfRoom(){
        return typeOfRoom;
    }
}
