package com.ecutbildning.hotelmanager.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Room {

    @Id
    public String id;

    boolean booked =false;
    boolean hasAC = false;
    boolean hasFreeBreakfest = false;
    int numberOfBeds, daysBooked, chargePerDay, fruitCharge, totalCost;
    ArrayList<String> orderedFood = new ArrayList<>();
    String typeOfRoom;



    public Room(String typeOfRoom) {

        this.typeOfRoom = typeOfRoom;
        switch(typeOfRoom.toLowerCase()) {
            case "poorsingle":
                this.setProperties(false, false, false, 1, 0, 150, "Poor Single Room");
                break;
            case "poordouble":
                this.setProperties(false, false, false, 2, 0, 250, "Poor Double Room");
                break;
            case "luxurysingle":
                this.setProperties(false, true, true, 1, 0, 1300, "Luxury Single Room");
                break;
            case "luxurloydouble":
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

    public void setChargePerDay(int value){
        this.chargePerDay = value;
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

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public int getFruitCharge() {
        return fruitCharge;
    }

    public void setFruitCharge(int fruitCharge) {
        this.fruitCharge = fruitCharge;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void updateTotalCost(){
        this.totalCost = this.fruitCharge + (this.daysBooked * this.chargePerDay);
    }

    public void setDaysBooked(int daysBooked) {
        this.daysBooked = daysBooked;
    }
}
