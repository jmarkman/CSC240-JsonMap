package com.learningprojects.jmarkman.jsonmap;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherAPI
{
    private WeatherAPI() { }

    /**
     * Fetches the JSON results for the URL
     *
     * @param stringUrl - the API url as a URL
     * @return - the JSON results as a String
     * @throws IOException
     */
    public static String getJSON(String stringUrl) throws IOException
    {
        URL url = null;

        try
        {
            url = new URL(stringUrl);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        // Establish connection to API
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // Actually read the data from the API
        InputStream stream = conn.getInputStream();
        // Use a scanner to read the contents retrieved from the stream and parse it
        Scanner scan = new Scanner(stream);
        scan.useDelimiter("\\A");

        try
        {
            if (scan.hasNext())
            {
                return scan.next();
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            Log.e("JSON Fetch Error", "Something went wrong while fetching JSON in getJSON");
            return null;
        }
        finally
        {
            // Regardless of the result, make sure that the connection
            // to the API is severed to prevent various errors and leaks
            conn.disconnect();
        }
    }

    /**
     * Fetches the JSON for each article and stores each article in an Article object
     *
     * @param json an ArrayList of strings that represent the JSON for each article
     * @return an ArrayList of Article objects
     */
    public static Weather getWeatherInfo(String json)
    {
        final String COORD = "coord";
        final String WEATHER = "weather";
        final String MAIN = "main";
        final String LATITUDE = "lat";
        final String LONGITUDE = "lon";
        final String DESCRIPTION = "description";
        final String TEMP_MAX = "temp_max";
        final String TEMP_MIN = "temp_min";

        try
        {
            JSONObject weatherJSON = new JSONObject(json);

            JSONObject jsonCoords = weatherJSON.getJSONObject(COORD);
            JSONArray weatherDetails = weatherJSON.getJSONArray(WEATHER);
            JSONObject weatherDetailsObject = weatherDetails.getJSONObject(0);
            JSONObject temperature = weatherJSON.getJSONObject(MAIN);

            Weather weather = new Weather(
              jsonCoords.getString(LATITUDE),
              jsonCoords.getString(LONGITUDE),
              weatherDetailsObject.getString(DESCRIPTION),
              temperature.getString(TEMP_MAX),
              temperature.getString(TEMP_MIN)
            );

            return weather;
        }
        catch(JSONException jse)
        {
            jse.printStackTrace();
        }

        return null;
    }
}
