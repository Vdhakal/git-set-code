package com.example.git_set_code.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.git_set_code.R;
import com.example.git_set_code.adapters.TripsAdapter;
import com.example.git_set_code.adapters.TripsAdapterOnline;
import com.example.git_set_code.networkChecker.CheckNetwork;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.TripClientData;
import com.example.git_set_code.trip_database.ViewModel.TripViewModel;
import com.example.git_set_code.utils.CustomToast;
import com.example.git_set_code.viewmodels.TripsData;
import com.example.git_set_code.utils.TripsDecorator;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphCardView;

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
    SwipeButton swipeButton;
    TripViewModel tripViewModel;
    boolean expanded;
    View rootView;
    ConstraintLayout constraintLayout;
    List<Integer> expandState;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thiscontext = this.getContext();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        rootView =  inflater.inflate(R.layout.home_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_trips_list);
        tripsDataList = new ArrayList<>();
        progressBar = (ProgressBar)rootView.findViewById(R.id.progress_circular);
        tripViewModel = new ViewModelProvider(requireActivity()).get(TripViewModel.class);
        tripViewModel.extractData();
        setUpUI();
        adapter.setActivity(getActivity());
        adapter.setTripViewModel(tripViewModel);
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
        if(expanded){
        }

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
            adapter = new TripsAdapter(getActivity(), new ArrayList<Trip>(), new ArrayList<SiteInformation>(),new ArrayList<SourceInformation>(),requireActivity());
            mRecyclerView.scrollToPosition(0);
//            adapter.setOnItemClickListner(new TripsAdapter.expandState() {
//                @Override
//                public void cardView(boolean expand) {
//                    CustomToast.showToast(getActivity(), expand+"");
//                    expanded = expand;
//                }
//
//            });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    public void setUpSlider(List<Trip> tripCbjectList, Activity activity) {
/*        swipeButton = (SwipeButton) rootView.findViewById(R.id.slideView);
        swipeButton.setText("sdsad");
            swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
                @Override
                public void onStateChange(boolean active) {
                    TripClientData tripClientData = new TripClientData(1,tripCbjectList.get(0).getTripId());
                    tripViewModel.setInsertTripClient(tripClientData);
                    CustomToast.showToast(activity, "Slider Called");

                    String msg = "";
                    if (active) {
                        msg = "Your trip has been selected!" + tripViewModel.getGetSelected();
                        tripViewModel.setSelection();
                    } else
                        msg = "Your trip has been selected!";
                    CustomToast.showToast(activity, msg);
                }
            });*/
        }


}