package com.example.mlichal_malek_weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{
    private Context context;
    private ArrayList<WeatherData> weatherDataArray;

    public WeatherAdapter(Context context, ArrayList<WeatherData> weatherDataArray) {
        this.context = context;
        this.weatherDataArray = weatherDataArray;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        WeatherData mod = weatherDataArray.get(position);
        holder.dayName.setText("Today");
        holder.descrip.setText(mod.getDesc());
        holder.temper.setText(mod.getTemp());
        holder.timeDay.setText(mod.getTime());
    }

    @Override
    public int getItemCount() {
        return weatherDataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView dayName, timeDay, temper, descrip;
        private ImageView iconImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.idDay);
            timeDay = itemView.findViewById(R.id.idTime);
            temper = itemView.findViewById(R.id.idTemp);
            descrip = itemView.findViewById(R.id.idDesc);
            iconImage = itemView.findViewById(R.id.iconWeather);
        }
    }
}
