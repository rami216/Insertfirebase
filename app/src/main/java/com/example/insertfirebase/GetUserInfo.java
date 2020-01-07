package com.example.insertfirebase;

public class GetUserInfo {


    private double Longitude;
    private double Laltitude;
    private String Name;

    public  GetUserInfo(){

    }

    public GetUserInfo(double laltitude, double longitude, String name) {
        Longitude = longitude;
        Laltitude = laltitude;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
