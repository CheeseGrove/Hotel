package com.ecutbildning.hotelmanager.demo;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Customers {
    @Id
    private String id;
    String bookingName;
    String bookedRoom;
    Date arrivingDate;
    Date leavingDate;
    int numberOfPeople;

    public Customers(String bookingName, String bookedRoom, Date arrivingDate, Date leavingDate, int numberOfPeople) {
        this.bookingName = bookingName;
        this.bookedRoom = bookedRoom;
        this.arrivingDate = arrivingDate;
        this.leavingDate = leavingDate;
        this.numberOfPeople = numberOfPeople;
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

    public String getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(String bookedRoom) {
        this.bookedRoom = bookedRoom;
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


    @Override
    public String toString() {
        return "Customers{" +
                "id='" + id + '\'' +
                ", bookingName='" + bookingName + '\'' +
                ", bookedRoom='" + bookedRoom + '\'' +
                ", arrivingDate=" + arrivingDate +
                ", leavingDate=" + leavingDate +
                ", numberOfPeople=" + numberOfPeople +
                '}';
    }
}
