package com.example.ass_th2007009.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ass_th2007009.R;
import com.example.ass_th2007009.model.Weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HourAdapter  extends RecyclerView.Adapter {
    private Activity activity;
    private List<Weather> listWeather;

    public HourAdapter(Activity activity, List<Weather> listWeather) {
        this.activity = activity;
        this.listWeather = listWeather;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.item_hour,parent,false);
        HourHolder holder = new HourHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HourHolder vh = (HourHolder) holder;
        Weather Weather = listWeather.get(position);
        vh.tvTime.setText(convertTime(Weather.getDateTime()));
        vh.tvTem.setText(Weather.getTemperature().getValue()+"");
        String url = "";
        if (Weather.getWeatherIcon() < 10){
            url = "https://developer.accuweather.com/sites/default/files/0" + Weather.getWeatherIcon() + "-s.png";
        }else {
            url = "https://developer.accuweather.com/sites/default/files/" + Weather.getWeatherIcon() + "-s.png";
        }
        Glide.with(activity).load(url).into(vh.icon);
    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    public static class HourHolder extends RecyclerView.ViewHolder{
        private TextView tvTime, tvTem;
        private ImageView icon;
        public HourHolder(View itemView){
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.time);
            tvTem = (TextView) itemView.findViewById(R.id.tem);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }

}