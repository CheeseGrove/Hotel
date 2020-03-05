package com.ecutbildning.hotelmanager.rooms;

import java.util.ArrayList;

public class LuxuryDoubleRoom extends Rooms {



    @Override
    public int getNumberOfBeds() {
        return 4;
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
        return 70000;
    }

}
