package com.ecutbildning.hotelmanager.rooms;

import java.util.ArrayList;

public class PoorDoubleRoom extends Rooms {

    @Override
    public int getNumberOfBeds() {
        return 4;
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
        return 340;
    }


}
