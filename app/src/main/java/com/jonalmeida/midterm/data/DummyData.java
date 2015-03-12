package com.jonalmeida.midterm.data;

import com.jonalmeida.midterm.CarInfo;

import java.util.ArrayList;

public class DummyData {

    public static ArrayList<CarInfo> DATA = new ArrayList<>();

    static {
        DATA.add(new CarInfo("Ferrari", "0 - 60 MPH in 2.5s", "F320i", "999-111-9999",
                "$10", "1.9%", "3.2%"));
        DATA.add(new CarInfo("Audi", "0 - 60 MPH in 2.0s", "R8", "999-222-9999",
                "$20", "2.0%", "3.5%"));
        DATA.add(new CarInfo("Ford", "0 - 60 MPH in 3.2s", "Edge", "999-333-9999",
                "$30", "2.1%", "3.9%"));
    }
}
