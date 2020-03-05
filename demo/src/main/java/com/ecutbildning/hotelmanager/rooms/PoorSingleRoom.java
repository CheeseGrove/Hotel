package com.ecutbildning.hotelmanager.rooms;

import java.util.ArrayList;

public class PoorSingleRoom extends Rooms {

    @Override
    public int getNumberOfBeds() {
        return 1;
    }

    @Override
    public boolean getAC() {
        return false;
    }

    @Override
    public boolean getBreakfast() {
        return false;
    }

    @Override
    public double getChargePerDay() {
        return 150;
    }

}
