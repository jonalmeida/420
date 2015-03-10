package com.jonalmeida.midterm.data;

import com.jonalmeida.midterm.CarInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DummyData {

    public static ArrayList<CarInfo> DATA = new ArrayList<>();

    static {
        DATA.add(new CarInfo("Ferrari", "0 - 60 MPH in 2.5s", "idk"));
        DATA.add(new CarInfo("Audi", "0 - 60 MPH in 2.0s", "R8"));
        DATA.add(new CarInfo("Ford", "0 - 60 MPH in 3.2s", "Edge"));
    }
}
