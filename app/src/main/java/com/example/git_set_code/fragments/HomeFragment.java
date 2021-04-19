package com.example.git_set_code.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.adapters.TripsAdapter;
import com.example.git_set_code.apiHelpers.TripsAPIService;
import com.example.git_set_code.adapters.TripsSummaryAdapter;
import com.example.git_set_code.cache.TripsDataRoom;
import com.example.git_set_code.viewmodels.TripsData;
import com.example.git_set_code.utils.TripsDecorator;
import com.example.git_set_code.viewmodels.TripsInformationViewModel;

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
    private TripsInformationViewModel tripsInformationViewModel;

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
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_trips_list);
        tripsDataList = new ArrayList<>();
        extractData();
//        tripsInformationViewModel = ViewModelProviders.of(this).get(TripsInformationViewModel.class);
//        tripsInformationViewModel.getAllData().observe(getViewLifecycleOwner(), new Observer<List<TripsDataRoom>>() {
//            @Override
//            public void onChanged(List<TripsDataRoom> tripsDataRooms) {
////                Toast.makeText(thiscontext, tripsInformationViewModel.getAllData().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void extractData(){
        TripsAPIService tripsAPIService = new TripsAPIService();
        tripsAPIService.getRequestedJson(thiscontext, tripsDataList, new TripsAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(thiscontext, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse() {
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.addItemDecoration(new TripsDecorator(20));
                adapter = new TripsAdapter(getActivity(), tripsDataList);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.scrollToPosition(0);

            }
        });
    }
}