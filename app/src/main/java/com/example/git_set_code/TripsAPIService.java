package com.example.git_set_code;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.git_set_code.adapters.TripsAdapter;
import com.example.git_set_code.adapters.TripsData;
import com.example.git_set_code.singletons.TripsRequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TripsAPIService {
    private static String JSON_GET_URL = "https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/GetTripListDetailByDriver/D1?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef";


    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse();
    }

    public void getRequestedJson(Context thiscontext, VolleyResponseListener volleyResponseListener){
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
                                    tripsData.setProductDesc(jsonObject.getString("ProductDesc"));
                                    tripsData.setDestinationName(jsonObject.getString("DestinationName"));
                                    tripsData.setAddress1(jsonObject.getString("Address1"));
                                    tripsData.setCity(jsonObject.getString("City"));
                                    tripsData.setStateAbbrev(jsonObject.getString("StateAbbrev"));
                                    tripsData.setRequestedQty(1);
                                    Toast.makeText(thiscontext, i+" "+tripsData.getCity(), Toast.LENGTH_LONG).show();
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
