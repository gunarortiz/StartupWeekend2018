package com.gunar.startupweekend.remote;

import com.gunar.startupweekend.model.HomeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("gunar/")
    Call<ArrayList<HomeResponse>> llamarGunar(@Field("nombre") String nombre);



    @FormUrlEncoded
    @POST("marco/")
    Call<ArrayList<HomeResponse>> llamarMarco(@Field("nombre") String nombre);


}