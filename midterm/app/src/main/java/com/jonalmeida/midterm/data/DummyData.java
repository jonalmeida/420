package com.jonalmeida.midterm.data;

import com.jonalmeida.midterm.CarInfo;

import java.util.ArrayList;

public class DummyData {

    public static ArrayList<CarInfo> DATA = new ArrayList<>();

    static {
        DATA.add(new CarInfo("Ferrari", "0 - 60 MPH in 2.5s", "F320i", "999-111-9999",
                "$10", "1.9%", "3.2%")); // 0
        DATA.add(new CarInfo("Audi", "0 - 60 MPH in 2.0s", "R8", "999-222-9999",
                "$20", "2.0%", "3.5%")); // 1
        DATA.add(new CarInfo("Ford", "0 - 60 MPH in 3.2s", "Edge", "999-333-9999",
                "$30", "2.1%", "3.9%")); // 2
        DATA.add(new CarInfo("Tesla", "0 - 60 MPH in 1.6s", "Model S", "999-444-9999",
                "$40", "4.8%", "2.5%")); // 3
        DATA.add(new CarInfo("Subaru", "0 - 60 MPH in 4.2s", "Impreza", "999-555-9999",
                "$50", "1.2%", "2.8%")); // 4
        DATA.add(new CarInfo("Chevrolet", "0 - 60 MPH in 4.2s", "Impala", "999-666-9999",
                "$60", "1.2%", "2.8%")); // 5
        DATA.add(new CarInfo("Audi", "0 - 60 MPH in 4.2s", "A6", "999-777-9999",
                "$70", "1.2%", "2.8%")); // 6
        DATA.add(new CarInfo("Toyota", "0 - 60 MPH in 4.2s", "Prius", "999-888-9999",
                "$80", "1.2%", "2.8%")); // 7
        DATA.add(new CarInfo("Buick", "0 - 60 MPH in 4.2s", "Regal", "999-999-9999",
                "$90", "1.2%", "2.8%")); // 8
        DATA.add(new CarInfo("Honda", "0 - 60 MPH in 4.2s", "Odyssey", "999-000-9999",
                "$100", "1.2%", "2.8%")); // 9
        DATA.add(new CarInfo("Subaru", "0 - 60 MPH in 4.2s", "Forester", "999-111-1999",
                "$110", "1.2%", "2.8%")); // 10
        DATA.add(new CarInfo("Toyota", "0 - 60 MPH in 4.2s", "Highlander", "999-222-2999",
                "$120", "1.2%", "2.8%")); // 11
        DATA.add(new CarInfo("Bugatti", "0 - 60 MPH in 2.4s", "Veyron", "999-333-3999",
                "$130", "1.2%", "2.8%")); // 12
        DATA.add(new CarInfo("Koenigsegg", "0 - 60 MPH in 2.9s", "Agera R", "999-444-4999",
                "$140", "1.2%", "2.8%")); // 13
        DATA.add(new CarInfo("McLaren", "0 - 60 MPH in 3.2s", "F1", "999-555-5999",
                "$150", "1.2%", "2.8%")); // 14
    }
}
