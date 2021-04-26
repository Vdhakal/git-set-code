package com.example.git_set_code.apiHelpers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.Truck;
import com.example.git_set_code.viewmodels.TripsData;
import com.example.git_set_code.singletons.TripsRequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TripsAPIService {
    private static String JSON_GET_URL = "https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/GetTripListDetailByDriver/D1?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef";

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse();
    }
    
    public void getRequestedJsonForRepo(Context thiscontext, List<Driver> driverObjectList, List<SiteInformation> siteInformationObjectList, List<SourceInformation> sourceInformationObjectList, List<Truck> truckObjectList, List<Trailer> trailerObjectList, List<Trip> tripObjectList, VolleyResponseListener volleyResponseListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, JSON_GET_URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equals("success")){
                                JSONObject obj = response.getJSONObject("data");
                                JSONArray resultSet1 = obj.getJSONArray("resultSet1");
                                for(int i=0; i<resultSet1.length(); i++){
                                    JSONObject jsonObject = resultSet1.getJSONObject(i);
                                    Driver driverObject = new Driver(jsonObject.getString("DriverCode"), jsonObject.getString("DriverName"));
                                    driverObjectList.add(driverObject);
                                    Trip tripObject = new Trip(jsonObject.getInt("TripId"), jsonObject.getString("TripName"), jsonObject.getString("TripDate"), jsonObject.getString("DriverCode"));
                                    tripObjectList.add(tripObject);
                                    SiteInformation siteInformationObject;
                                    SourceInformation sourceInformationObject;
                                    if(jsonObject.getString("WaypointTypeDescription").equals("Site Container")){
                                        siteInformationObject = new SiteInformation(jsonObject.getString("SiteContainerCode"),jsonObject.getString("SiteContainerDescription"),jsonObject.getInt("DelReqNum"), jsonObject.getInt("DelReqLineNum"),jsonObject.getInt("ProductId"),jsonObject.getString("ProductCode"), jsonObject.getString("ProductDesc"),jsonObject.getInt("RequestedQty"),  jsonObject.getString("UOM"), jsonObject.getString("Fill"), jsonObject.getDouble("Latitude"),jsonObject.getDouble("Longitude"),jsonObject.getString("DestinationCode"),jsonObject.getString("DestinationName"),jsonObject.getString("Address1"), jsonObject.getString("Address2"), jsonObject.getString("City"), jsonObject.getString("StateAbbrev"),jsonObject.getInt("PostalCode"),jsonObject.getInt("SeqNum"),jsonObject.getString("WaypointTypeDescription"),jsonObject.getInt("TripId"));
                                        siteInformationObjectList.add(siteInformationObject);
                                    }
                                    else {
                                        sourceInformationObject = new SourceInformation(jsonObject.getInt("SeqNum"),jsonObject.getString("WaypointTypeDescription"),jsonObject.getDouble("Latitude"),jsonObject.getDouble("Longitude"),jsonObject.getString("DestinationCode"),jsonObject.getString("DestinationName"),jsonObject.getString("Address1"), jsonObject.getString("Address2"), jsonObject.getString("City"), jsonObject.getString("StateAbbrev"),jsonObject.getInt("PostalCode"), jsonObject.getInt("TripId"));
                                        sourceInformationObjectList.add(sourceInformationObject);
                                    }
                                    Truck truckObject = new Truck(jsonObject.getInt("TruckId"),jsonObject.getString("TruckCode"),jsonObject.getString("TruckDesc"), jsonObject.getString("DriverCode"));
                                    truckObjectList.add(truckObject);
                                    Trailer trailerObject = new Trailer(jsonObject.getInt("TrailerId"),jsonObject.getString("TrailerCode"),jsonObject.getString("TrailerDesc"), jsonObject.getInt("TruckId"));
                                    trailerObjectList.add(trailerObject);

                                }
                            }
                        } catch (JSONException e) {
                            Toast.makeText(thiscontext, "Something's Wrong! Please wait a while.", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                        volleyResponseListener.onResponse();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError("Could not fetch data. Make sure you're connected to the internet.");
                        // TODO: Handle error
                    }
                });

        TripsRequestSingleton.getInstance(thiscontext).addToRequestQueue(jsonObjectRequest);
    }
}
