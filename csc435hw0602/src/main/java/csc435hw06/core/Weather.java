package csc435hw06.core;


import java.util.ArrayList;

/*
 * A Weather class. This is for mapping the data retrieved from the
 * OpenWeatherMap API from JSON into something usable in a SQL query.
 * The data is mapped into three classes: Weather, Data, and Location.
 * Location contains the latitude and longitude for the data.
 * Data contains the actual data retrieved -- the value, the pressure,
 * and the precision. Weather contains the time associated with the
 * data, the Location of the data, and an ArrayList of the Data from
 * the API, since are multiple Data associated with a Time and Location.
 *The time, latitude, and longitude retrieved from the API will
 * be different from the input the user gives to the controller.
 */

public class Weather {

    String time = "";
    Coords location;
    ArrayList<Readings> data = new ArrayList();

    public String getTime() {
        return time;
    }

    public Coords getLocation() {
        return location;
    }

    public ArrayList<Readings> getData() {
        return data;
    }

    public void setTime(String t) {
        time = t;
    }

    public void setLocation(Coords l) {
        location = l;
    }

    public void setData(ArrayList<Readings> d) {
        data = d;
    }
}