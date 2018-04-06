package com.learningprojects.jmarkman.jsonmap;

public class Weather
{
    private String latitude;
    private String longitude;
    private String weatherDetails;
    private String maxTemp;
    private String minTemp;

    public Weather(String latitude, String longitude, String weatherDetails, String maxTemp, String minTemp)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.weatherDetails = weatherDetails;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public Double getLatitude()
    {
        Double dblLat = Double.parseDouble(this.latitude);
        return dblLat;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        Double dblLong = Double.parseDouble(this.longitude);
        return dblLong;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getWeatherDetails()
    {
        return weatherDetails;
    }

    public void setWeatherDetails(String weatherDetails)
    {
        this.weatherDetails = weatherDetails;
    }

    public String getMaxTemp()
    {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp)
    {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp()
    {
        return minTemp;
    }

    public void setMinTemp(String minTemp)
    {
        this.minTemp = minTemp;
    }

    public String getSnippet()
    {
        String markerSnippet = "";
        markerSnippet += "Max Temperature: " + this.maxTemp + "\n";
        markerSnippet += "Min Temperature: " + this.minTemp + "\n";
        markerSnippet += "Current forecast: " + this.weatherDetails;

        return markerSnippet;
    }
}
