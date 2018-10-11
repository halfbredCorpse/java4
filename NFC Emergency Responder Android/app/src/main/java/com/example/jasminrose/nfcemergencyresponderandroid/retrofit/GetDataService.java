package com.example.jasminrose.nfcemergencyresponderandroid.retrofit;

import com.example.jasminrose.nfcemergencyresponderandroid.retrofit.entities.RetroRecord;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {

    @POST("WSNfc/webresources/entities.servicedb.remoterecords/json")
    Call<RetroRecord> createRecord(@Body RetroRecord retroRecord);

    @GET("WSNfc/webresources/entities.servicedb.remoterecords/count")
    Call<String> count();

}
