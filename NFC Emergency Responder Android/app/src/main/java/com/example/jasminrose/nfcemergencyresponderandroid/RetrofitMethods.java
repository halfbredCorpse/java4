package com.example.jasminrose.nfcemergencyresponderandroid;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jasminrose.nfcemergencyresponderandroid.retrofit.GetDataService;
import com.example.jasminrose.nfcemergencyresponderandroid.retrofit.RetrofitClientInstance;
import com.example.jasminrose.nfcemergencyresponderandroid.retrofit.entities.RetroRecord;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitMethods {

    GetDataService service;
    RetroRecord retroRecord;

    Context context;


    public RetrofitMethods(Context context) {
        this.context = context;

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }

    public void createRecord(String userId, String dateTime, String location) {
        Call<String> count = service.count();
        count.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                retroRecord = new RetroRecord();
                retroRecord.setPk(Integer.parseInt(Objects.requireNonNull(response.body()).toString()));
                Log.i("countItem", String.valueOf(Integer.parseInt(Objects.requireNonNull(response.body()).toString())));

                retroRecord.setUserId(userId);
                retroRecord.setDateTime(dateTime);
                retroRecord.setLocation(location);

                Call<RetroRecord> createRecord = service.createRecord(retroRecord);
                createRecord.enqueue(new Callback<RetroRecord>() {
                    @Override
                    public void onResponse(Call<RetroRecord> call, Response<RetroRecord> response) {
                        Toast.makeText(context, "Added emergency record to remote database", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RetroRecord> call, Throwable t) {
                        Toast.makeText(context, "Unable to save emergency record to database", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
