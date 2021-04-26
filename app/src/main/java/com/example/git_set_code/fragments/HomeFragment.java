package com.example.git_set_code.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.adapters.TripsAdapter;
import com.example.git_set_code.adapters.TripsAdapterOnline;
import com.example.git_set_code.networkChecker.CheckNetwork;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.ViewModel.TripViewModel;
import com.example.git_set_code.viewmodels.TripsData;
import com.example.git_set_code.utils.TripsDecorator;

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
    private ProgressBar progressBar;
    private boolean completed = false;
    List<TripsData> tripsDataList;
    Context thiscontext;
    TripsAdapter adapter;
    TripsAdapterOnline onlineAdapter;

    TripViewModel tripViewModel;

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
        progressBar = (ProgressBar)rootView.findViewById(R.id.progress_circular);
        tripViewModel = new ViewModelProvider(requireActivity()).get(TripViewModel.class);
        setUpUI();
        tripViewModel.extractData();
        CheckNetwork.checkNetworkInfo(thiscontext, new CheckNetwork.OnConnectionStatusChange() {
            @Override
            public void onChange(boolean type) {
                if(type){
                    //extractData();
                }else {
//                    setUpUI();
                }
            }
        });
        setUpObservers();

        return rootView;
    }

    private void setUpObservers() {

        tripViewModel.getAllTrip().observe(requireActivity(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                progressBar.setVisibility(View.GONE);
                adapter.setTripObjectList(trips);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
        });

        tripViewModel.getAllSource().observe(requireActivity(), new Observer<List<SourceInformation>>() {
            @Override
            public void onChanged(List<SourceInformation> sourceInformations) {
                adapter.setSourceInformationObjectList(sourceInformations);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
        });

        tripViewModel.getAllSite().observe(requireActivity(), new Observer<List<SiteInformation>>() {
            @Override
            public void onChanged(List<SiteInformation> siteInformations) {
                adapter.setSiteInformationObjectList(siteInformations);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
        });


    }

    private void setUpUI() {
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.addItemDecoration(new TripsDecorator(20));
            adapter = new TripsAdapter(getActivity(), new ArrayList<Trip>(), new ArrayList<SiteInformation>(),new ArrayList<SourceInformation>());
            mRecyclerView.scrollToPosition(0);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}