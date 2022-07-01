package com.example.ass_th2007009.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ass_th2007009.R;
import com.example.ass_th2007009.adapter.HourAdapter;
import com.example.ass_th2007009.model.Weather;
import com.example.ass_th2007009.network.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView hour;
    private TextView tem, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tem = (TextView) findViewById(R.id.tem);
        status = (TextView) findViewById(R.id.status);

        getHours();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        hour = (RecyclerView) findViewById(R.id.hour);
        hour.setLayoutManager(layoutManager);
    }

    private void getHours() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.gethour().enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if (response.body() == null) return;

                List<Weather> listWeather = response.body();
                HourAdapter adapter = new HourAdapter(MainActivity.this, listWeather);
                hour.setAdapter(adapter);

                Weather Weather = listWeather.get(0);
                tem.setText(Weather.getTemperature().getValue().intValue() + "°");
                status.setText(Weather.getIconPhrase());
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {

            }
        });
    }
}