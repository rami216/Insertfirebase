package com.example.insertfirebase;

public class CurrentLocation {

    private double Longitude;
    private double Laltitude;
    private String Name;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public CurrentLocation(double laltitude, double longitude, String name)
    {
        Longitude = longitude;
        Laltitude =  laltitude;
        Name = name;

    }

    public double getLongitude() {
        return Longitude;
    }



    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLaltitude() {
        return Laltitude;
    }

    public void setLaltitude(double laltitude) {
        Laltitude = laltitude;
    }
}
