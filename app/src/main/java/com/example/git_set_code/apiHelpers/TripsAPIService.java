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
import com.example.git_set_code.trip_database.Table.TripClientData;
import com.example.git_set_code.trip_database.Table.Truck;
import com.example.git_set_code.viewmodels.TripsData;
import com.example.git_set_code.singletons.TripsRequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * This Activity handles the Trip API service
 */
public class TripsAPIService {
    private static String JSON_GET_URL = "https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/GetDetailedTripListByDriver/gitsetcode?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef";
//    https://api.appery.io/rest/1/apiexpress/api/DispatcherMobileApp/TripProductPickupPut/gitsetcode/183/24/887/12/2021-05-03%2000:00:00.0000000/2021-05-03%2000:00:00.0000000/10/10?apiKey=f20f8b25-b149-481c-9d2c-41aeb76246ef

    /**
     * This interface provides two abstract methods which can be implemented according to required needs
     */
    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse();
    }

    /**
     * This method gets the requested Json for the repo
     * @param thiscontext, a Context object
     * @param driverObjectList, list of Driver objects
     * @param siteInformationObjectList, list of siteInformation
     * @param sourceInformationObjectList, a list of sourceInformation
     * @param truckObjectList, a list of truckObjects
     * @param trailerObjectList, a list of trailerObjects
     * @param tripObjectList, a list of tripObjects
     * @param tripClientDataList, a list of tripClientData
     * @param volleyResponseListener, a list of VolleyResponseListener object
     */
    public void getRequestedJsonForRepo(Context thiscontext, List<Driver> driverObjectList, List<SiteInformation> siteInformationObjectList, List<SourceInformation> sourceInformationObjectList, List<Truck> truckObjectList, List<Trailer> trailerObjectList, List<Trip> tripObjectList, List<TripClientData> tripClientDataList, VolleyResponseListener volleyResponseListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, JSON_GET_URL, null, new Response.Listener<JSONObject>() {

                    /**
                     *  This method handles the response
                     * @param response, a JSON object
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("status").equals("success")) {
                                JSONObject obj = response.getJSONObject("data");
                                JSONArray resultSet1 = obj.getJSONArray("resultSet1");
                                for (int i = 0; i < resultSet1.length(); i++) {
                                    JSONObject jsonObject = resultSet1.getJSONObject(i);
                                    Driver driverObject = new Driver(jsonObject.getString("DriverCode"), jsonObject.getString("DriverName"));
                                    driverObjectList.add(driverObject);
                                    Trip tripObject = new Trip(jsonObject.getInt("TripId"), jsonObject.getString("TripName"), jsonObject.getString("TripDate"), jsonObject.getString("DriverCode"));
                                    tripObjectList.add(tripObject);
                                    SiteInformation siteInformationObject;
                                    SourceInformation sourceInformationObject;
                                    if (jsonObject.getString("WaypointTypeDescription").equals("Site Container")) {
                                        siteInformationObject = new SiteInformation(jsonObject.getString("SiteContainerCode"), jsonObject.getString("SiteContainerDescription"), jsonObject.getInt("DelReqNum"), jsonObject.getInt("DelReqLineNum"), jsonObject.getInt("ProductId"), jsonObject.getString("ProductCode"), jsonObject.getString("ProductDesc"), jsonObject.getInt("RequestedQty"), jsonObject.getString("UOM"), jsonObject.getString("Fill"), jsonObject.getDouble("Latitude"), jsonObject.getDouble("Longitude"), jsonObject.getString("DestinationCode"), jsonObject.getString("DestinationName"), jsonObject.getString("Address1"), jsonObject.getString("Address2"), jsonObject.getString("City"), jsonObject.getString("StateAbbrev"), jsonObject.getInt("PostalCode"), jsonObject.getInt("SeqNum"), jsonObject.getString("WaypointTypeDescription"), jsonObject.getInt("TripId"));
                                        siteInformationObjectList.add(siteInformationObject);
                                    } else {
                                        sourceInformationObject = new SourceInformation(jsonObject.getInt("SeqNum"), jsonObject.getString("WaypointTypeDescription"), jsonObject.getDouble("Latitude"), jsonObject.getDouble("Longitude"), jsonObject.getString("DestinationCode"), jsonObject.getString("DestinationName"), jsonObject.getString("Address1"), jsonObject.getString("Address2"), jsonObject.getString("City"), jsonObject.getString("StateAbbrev"), jsonObject.getInt("PostalCode"), jsonObject.getInt("TripId"), jsonObject.getInt("SourceID"));
                                        sourceInformationObjectList.add(sourceInformationObject);
                                    }
                                    Truck truckObject = new Truck(jsonObject.getInt("TruckId"), jsonObject.getString("TruckCode"), jsonObject.getString("TruckDesc"), jsonObject.getString("DriverCode"));
                                    truckObjectList.add(truckObject);
                                    Trailer trailerObject = new Trailer(jsonObject.getInt("TrailerId"), jsonObject.getString("TrailerCode"), jsonObject.getString("TrailerDesc"), jsonObject.getInt("TruckId"));
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

                    /**
                     *
                     * @param error, a VolleyError
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError("Could not fetch data. Make sure you're connected to the internet.");
                        // TODO: Handle error
                    }
                });

        TripsRequestSingleton.getInstance(thiscontext).addToRequestQueue(jsonObjectRequest);
    }
}
