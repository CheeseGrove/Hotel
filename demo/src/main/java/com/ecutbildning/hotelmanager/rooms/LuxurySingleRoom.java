package com.ecutbildning.hotelmanager.rooms;

import java.util.ArrayList;

public class LuxurySingleRoom extends Rooms {

    public LuxurySingleRoom() {

    }

    @Override
    public int getNumberOfBeds() {
        return 1;
    }

    @Override
    public boolean getAC() {
        return true;
    }

    @Override
    public boolean getBreakfast() {
        return true;
    }

    @Override
    public double getChargePerDay() {
        return 39949;
    }



}
