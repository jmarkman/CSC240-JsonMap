package com.learningprojects.jmarkman.jsonmap;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Weather nccWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        String nccWeatherApi = "http://api.openweathermap.org/data/2.5/weather?id=5118226&APPID=cff54cf4393ce98abf4134eca01c4b27&units=imperial";

        try
        {
            // https://stackoverflow.com/a/14827618
            nccWeather = new WeatherQueryTask().execute(nccWeatherApi).get();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        mMap = googleMap;

        LatLng nassau = new LatLng(nccWeather.getLatitude(),nccWeather.getLongitude());
        https://stackoverflow.com/a/13904784
        mMap.addMarker(new MarkerOptions().position(nassau).title("Nassau Weather").snippet(nccWeather.getSnippet()));
        mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nassau));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nassau, 12));
    }

    public class WeatherQueryTask extends AsyncTask<String, Void, Weather>
    {
        @Override
        protected Weather doInBackground(String... str)
        {
            String weatherJson = null;
            Weather weather = null;
            try
            {
                weatherJson = WeatherAPI.getJSON(str[0]);
                weather = WeatherAPI.getWeatherInfo(weatherJson);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather)
        {
            nccWeather = weather;
        }
    }
}
