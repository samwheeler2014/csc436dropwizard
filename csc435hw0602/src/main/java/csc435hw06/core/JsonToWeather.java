package csc435hw06.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class JsonToWeather {

    public Weather toWeather(Location l,  String date) throws IOException {
        //get the Location's latitude and longitude -- used to define url to connect to external API
        int latitude = l.getLatitude();
        int longitude = l.getLongitude();
        //for optimal results, only give a year
        //build url to access API
        URL url = new URL("http://api.openweathermap.org/pollution/v1/co/" + latitude + "," + longitude + "/" + date + "Z.json?appid=42c22bba28eaeee8f1e68b38da9edaba");
        //map the JSON result from the url into a Weather object for parsing and for database use.
        ObjectMapper om = new ObjectMapper();
        Weather w = om.readValue(url, Weather.class);
        return w;
    }

}
