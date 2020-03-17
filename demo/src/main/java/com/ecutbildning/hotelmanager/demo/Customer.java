package com.ecutbildning.hotelmanager.demo;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Customer {
    @Id
    private String id;
    String bookingName;
    Date arrivingDate;
    Date leavingDate;
    int daysStaying;
    int numberOfPeople;
    int billToPay;
    ArrayList<String> bookedRooms = new ArrayList<>();


    public Customer(String bookingName, Date arrivingDate, Date leavingDate, int numberOfPeople) {
        this.bookingName = bookingName;
        this.arrivingDate = arrivingDate;
        this.leavingDate = leavingDate;
        this.numberOfPeople = numberOfPeople;
        this.billToPay = 0;
    }

    public int getDaysStaying() {
        return daysStaying;
    }

    public void setDaysStaying(int daysStaying) {
        this.daysStaying = daysStaying;
    }

    public ArrayList<String> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(ArrayList<String> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public Date getArrivingDate() {
        return arrivingDate;
    }

    public void setArrivingDate(Date arrivingDate) {
        this.arrivingDate = arrivingDate;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getBillToPay() {
        return billToPay;
    }

    public void setBillToPay(int billToPay) {
        this.billToPay = billToPay;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id='" + id + '\'' +
                ", bookingName='" + bookingName + '\'' +
                ", arrivingDate=" + arrivingDate +
                ", leavingDate=" + leavingDate +
                ", numberOfPeople=" + numberOfPeople +
                ", bookedRooms='" + bookedRooms + '\'' +
                '}';
    }
}
