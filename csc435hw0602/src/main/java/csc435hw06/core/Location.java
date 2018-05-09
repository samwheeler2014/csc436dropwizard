package csc435hw06.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

public class Location {

    /*
     * Location class corresponding to the Location table in the database.
     * This class has variables for each column in the Location table.
     */

    @Id
    private long id;

    @JsonProperty
    String name;
    @JsonProperty
    int latitude;
    @JsonProperty
    int longitude;

    public Location(long i, String n, int la, int lo) {
        id = i;
        name = n;
        latitude = la;
        longitude = lo;
    }

    public String getName() { return name;}

    public long getId() { return id; }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setName(String n) {name = n;}

    public void setLatitude(int l) {
        latitude = l;
    }

    public void setLongitude(int l) {
        longitude = l;
    }

}
