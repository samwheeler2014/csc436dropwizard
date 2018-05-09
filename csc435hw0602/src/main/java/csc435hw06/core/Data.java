package csc435hw06.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

public class Data {

    /*
     * Data class corresponding to the Data table in the database.
     * This class has variables for each column in the Data table.
     */

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @JsonProperty
    long location_id;
    @JsonProperty
    String data_date;
    @JsonProperty
    float precision;
    @JsonProperty
    float pressure;
    @JsonProperty
    String value;

    public Data(long i, long lid, String dd, float prec, float pres, String v) {
        id = i;
        location_id = lid;
        data_date = dd;
        precision = prec;
        pressure = pres;
        value = v;
    }

    public long getId() { return id; }

    public long getLocation_id() {return location_id;}

    public String getData_date() {return data_date;}

    public float getPrecision() {
        return precision;
    }

    public float getPressure() {
        return pressure;
    }

    public String getValue() {
        return value;
    }

    public void setLocation_id(long l) {location_id = l;}

    public void setData_date(String d) {data_date = d;}

    public void setPrecision(float p) {
        precision = p;
    }

    public void setPressure(float p) {
        pressure = p;
    }

    public void setValue(String v) {
        value = v;
    }



}
