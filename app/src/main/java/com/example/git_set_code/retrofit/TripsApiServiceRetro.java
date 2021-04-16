package com.example.git_set_code.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TripsApiServiceRetro {
    @GET("GetTripListDetailByDriver/D1?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef")
    Call<List<TripNetworkEntity>> getTripData();
}
