package com.example.git_set_code.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.git_set_code.R;
import com.example.git_set_code.TripsAPIService;
import com.example.git_set_code.adapters.TripsAdapter;
import com.example.git_set_code.adapters.TripsData;
import com.example.git_set_code.singletons.TripsRequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    List<TripsData> tripsDataList;
    Context thiscontext;
    TripsAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thiscontext = this.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.home_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.trip_summary_list);
        tripsDataList = new ArrayList<>();
        extractData();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void extractData(){
        TripsAPIService tripsAPIService = new TripsAPIService();
        tripsAPIService.getRequestedJson(thiscontext, new TripsAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(thiscontext, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse() {
                mLayoutManager = new LinearLayoutManager(getActivity());
                adapter = new TripsAdapter(getActivity(), tripsDataList);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.scrollToPosition(0);
            }
        });
    }
}