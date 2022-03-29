package com.example.mlichal_malek_weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private WeatherData weather;
    private String unit;
    private TextView local, night, even, morn, day, visi, uvL, humi, winder, descript, feeling, tempera, timeNow, ris, set;
    private WeatherAdapter weatherAdapter;
    private ArrayList<WeatherData> weatherDataArray;
    private RecyclerView weatherRecycle;
    private ImageView weathIcon;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean canWork = hasNetworkConnection();
        if(canWork = true) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    onBackPressed();
                    return true;

                case R.id.unit:
                    if (unit.equals("imperial")) {
                        item.setIcon(R.drawable.units_c);
                        unit = "metric";
                        getWeatherData(unit, "Chicago");//takeout
                        return true;
                    }
                    if (unit.equals("metric")) {
                        item.setIcon(R.drawable.units_f);
                        unit = "imperial";
                        getWeatherData(unit, "Chicago");//takeout
                        return true;
                    }

                case R.id.day:
                    startActivity(new Intent(MainActivity.this, DailyActivity.class));
                    return true;
                case R.id.loc:

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }else{
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unit = "imperial";
        weatherRecycle = findViewById(R.id.weatherRe);
        local = findViewById(R.id.place);
        night = findViewById(R.id.tempNite);
        even = findViewById(R.id.tempEven);
        morn = findViewById(R.id.tempMorn);
        day = findViewById(R.id.tempDay);
        visi = findViewById(R.id.vis);
        uvL = findViewById(R.id.uv);
        humi = findViewById(R.id.hum);
        winder = findViewById(R.id.wind);
        descript = findViewById(R.id.desc);
        feeling = findViewById(R.id.realFeel);
        tempera = findViewById(R.id.curTemp);
        timeNow = findViewById(R.id.now);
        ris = findViewById(R.id.sunRise);
        set = findViewById(R.id.sunSet);
        weathIcon = findViewById(R.id.iconWeather);
        weatherDataArray = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(this, weatherDataArray);
        weatherRecycle.setAdapter(weatherAdapter);
        weatherRecycle.setLayoutManager(new LinearLayoutManager(this));

        getWeatherData(unit, "Austin");//change/get rid of
    }


    public void getWeatherData(String unit, String userLocation) {
        double[] lonLat = getLatLon(userLocation);
        double lat = lonLat[0];
        double lon = lonLat[1];
        String call = "https://api.openweathermap.org/data/2.5/onecall?lat="+lat+"&lon="+lon+"&appid=c5507ee90dedb4bbccc26c1b8b6ce02b&units="+unit+"&lang=en&exclude=minutely";

        RequestQueue requested = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonOb = new JsonObjectRequest(Request.Method.GET, call, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                weatherDataArray.clear();

                try {
                    String temperature = response.getJSONObject("current").getString("temp");
                    if(unit.equals("imperial")){
                        tempera.setText((int)Math.round(Float.parseFloat(temperature))+"℉");
                    }
                    if(unit.equals("metric")){
                        tempera.setText((int)Math.round(Float.parseFloat(temperature))+"°C");
                    }

                    String feelsLike = response.getJSONObject("current").getString("feels_like");
                    if(unit.equals("imperial")){
                        feeling.setText("Feels Like: "+(int)Math.round(Float.parseFloat(feelsLike))+"℉");
                    }
                    if(unit.equals("metric")){
                        feeling.setText("Feels Like: "+(int)Math.round(Float.parseFloat(feelsLike))+"°C");
                    }

                    String visual = response.getJSONObject("current").getString("visibility");
                    if(unit.equals("imperial")){
                        visi.setText("Visibility: " + visual + "mi");
                    }
                    if(unit.equals("metric")){
                        visi.setText("Visibility: " + visual+"km");
                    }

                    String sunset = response.getJSONObject("current").getString("sunset");
                    set.setText(sunset);

                    String uvLight = response.getJSONObject("current").getString("uvi");
                    uvL.setText("UV Index: "+uvLight);

                    String humid = response.getJSONObject("current").getString("humidity");
                    humi.setText("Humidity: " +humid+"%");

                    String loca = CityLocal(userLocation);
                    local.setText(loca);

                    String timezone_offset = response.getJSONObject("current").getString("timezone");

                    String current = response.getJSONObject("current").getString("dt");
                    timeNow.setText(whatTime(Long.parseLong(current), Long.parseLong(timezone_offset)));

                    String sunrise = response.getJSONObject("current").getString("sunrise");
                    ris.setText(whatTime(Long.parseLong(sunrise), Long.parseLong(timezone_offset)));

                    String describer = response.getJSONObject("current").getJSONObject("weather").getString("description");
                    descript.setText(describer);

                    String clouds = response.getJSONObject("current").getJSONObject("weather").getString("main");
                    winder.setText(clouds);







                    String icon = response.getJSONObject("current").getJSONObject("weather").getString("icon");
                    String iconCode = "_" + icon;
                    int iconResId = getResources().getIdentifier(iconCode , "drawable", getPackageName());
                    weathIcon.setImageResource(iconResId);

                    JSONObject forecastObj = response.getJSONObject("current");
                    JSONObject forecastD= forecastObj.getJSONArray("hourly").getJSONObject(0);
                    JSONArray hourArray = forecastD.getJSONArray("");
                    for(int i = 0; i<hourArray; i++){

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requested.add(jsonOb);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String whatTime(Long time, Long timezone){
        LocalDateTime ldt = LocalDateTime.ofEpochSecond(time + timezone, 0, ZoneOffset.UTC);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MMM dd h:mm a, yyyy", Locale.getDefault());
        String formattedTime = ldt.format(dtf); // Thu Sep 30 10:06 PM, 2021

        return formattedTime;
    }

    private String CityLocal(String userProvidedLocation){
        String location = null;
        Geocoder geo = new Geocoder(this); // Here, “this” is an Activity
        try {
            List<Address> address = geo.getFromLocationName(userProvidedLocation, 1);

            if (address == null || address.isEmpty()) {
                // Nothing returned!
                return null;
            }
            if (address.size() > 0) {
                location = address.get(0).getLocality()+", "+address.get(0).getAdminArea();
            }

        } catch (IOException e) {
            // Failure to get an Address object
        }
        return location;
    }

    private double[] getLatLon(String userProvidedLocation) {
        Geocoder geocoder = new Geocoder(this); // Here, “this” is an Activity
        try {
            List<Address> address = geocoder.getFromLocationName(userProvidedLocation, 1);

            if (address == null || address.isEmpty()) {
                // Nothing returned!
                return null;
            }
            double lat = address.get(0).getLatitude();
            double lon = address.get(0).getLongitude();

            return new double[] {lat, lon};
        } catch (IOException e) {
            // Failure to get an Address object
            return null;
        }
    }

private String getDirection(double degrees) {
    if (degrees >= 337.5 || degrees < 22.5)
        return "N";
    if (degrees >= 22.5 && degrees < 67.5)
        return "NE";
    if (degrees >= 67.5 && degrees < 112.5)
        return "E";
    if (degrees >= 112.5 && degrees < 157.5)
        return "SE";
    if (degrees >= 157.5 && degrees < 202.5)
        return "S";
    if (degrees >= 202.5 && degrees < 247.5)
        return "SW";
    if (degrees >= 247.5 && degrees < 292.5)
        return "W";
    if (degrees >= 292.5 && degrees < 337.5)
        return "NW";
    return "X"; // We'll use 'X' as the default if we get a bad value
}

    private boolean hasNetworkConnection() {
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

}