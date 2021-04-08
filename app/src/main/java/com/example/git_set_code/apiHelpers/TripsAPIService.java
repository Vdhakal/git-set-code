package com.example.git_set_code.apiHelpers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

    public void getRequestedJson(Context thiscontext, List<TripsData> tripsDataList, VolleyResponseListener volleyResponseListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, JSON_GET_URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equals("success")){
                                JSONObject obj = response.getJSONObject("data");
                                JSONArray resultSet2 = obj.getJSONArray("resultSet2");
                                JSONArray resultSet1 = obj.getJSONArray("resultSet1");
                                for(int i=0; i<resultSet1.length(); i++){
                                    JSONObject jsonObject = resultSet1.getJSONObject(i);
                                    TripsData tripsData = new TripsData();
                                    if(!jsonObject.isNull("ProductDesc")) tripsData.setProductDesc(jsonObject.getString("ProductDesc"));
                                    else {tripsData.setProductDesc("NONE");}
                                    tripsData.setDestinationCod(jsonObject.getString("DestinationCode"));
                                    tripsData.setDestinationName(jsonObject.getString("DestinationName"));
                                    tripsData.setAddress1(jsonObject.getString("Address1"));
                                    tripsData.setCity(jsonObject.getString("City"));
                                    tripsData.setWaypointTypeDescription(jsonObject.getString("WaypointTypeDescription"));
                                    tripsData.setStateAbbrev(jsonObject.getString("StateAbbrev"));
                                    if(jsonObject.isNull("RequestedQty")) tripsData.setRequestedQty(0);
                                    else {
                                        tripsData.setRequestedQty(jsonObject.getInt("RequestedQty"));
                                    }
                                    tripsDataList.add(tripsData);
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
