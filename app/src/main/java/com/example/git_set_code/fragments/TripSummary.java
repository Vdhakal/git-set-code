package com.example.git_set_code.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.git_set_code.R;
import com.example.git_set_code.adapters.SiteSummaryAdapter;
import com.example.git_set_code.adapters.SourceSummaryAdapter;
import com.example.git_set_code.networkChecker.CheckNetwork;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.TripClientData;
import com.example.git_set_code.trip_database.ViewModel.TripViewModel;
import com.example.git_set_code.utils.CustomToast;
import com.example.git_set_code.viewmodels.TripsData;
import com.example.git_set_code.utils.TripsDecorator;

import java.util.ArrayList;
import java.util.List;

import ng.max.slideview.SlideView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment} factory method to
 * create an instance of this fragment.
 */
public class TripSummary extends Fragment {
    protected RecyclerView siteRecyclerView;
    protected RecyclerView sourceRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private SlideView slideView;
    private ProgressBar progressBar;
    private TripViewModel tripViewModel;
    ColorStateList def;
    List<TripsData> tripsDataList;
    Context thiscontext;
    SiteSummaryAdapter siteSummaryAdapter;
    SourceSummaryAdapter sourceSummaryAdapter;
    TextView source_tv;
    TextView site_tv;
    TextView select;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thiscontext = this.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView =  inflater.inflate(R.layout.fragment_trip_summary, container, false);
        siteRecyclerView = (RecyclerView) rootView.findViewById(R.id.site_list);
        sourceRecyclerView = (RecyclerView) rootView.findViewById(R.id.source_list);
        tripsDataList = new ArrayList<>();
        progressBar= (ProgressBar)rootView.findViewById(R.id.progress_circular);
        source_tv = rootView.findViewById(R.id.item1);
        site_tv = rootView.findViewById(R.id.item2);
        select = rootView.findViewById(R.id.select);
        tripViewModel = new ViewModelProvider(requireActivity()).get(TripViewModel.class);
        def = site_tv.getTextColors();
        setUpSourceObservers();
        navigateSourceSite();
        setUpSlider(rootView);


        CheckNetwork.checkNetworkInfo(thiscontext, new CheckNetwork.OnConnectionStatusChange() {
            @Override
            public void onChange(boolean type) {
                if(type){
//                    extractData();
                }else {
//                    setUpUI();
                }
            }
        });

        return rootView;
    }

    private void navigateSourceSite() {
        source_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select.animate().x(0).setDuration(100);
                source_tv.setTextColor(Color.WHITE);
                site_tv.setTextColor(def);
                sourceRecyclerView.setVisibility(View.VISIBLE);
                siteRecyclerView.setVisibility(View.GONE);
                setUpSourceObservers();
                site_tv.setBackground(getResources().getDrawable(R.drawable.back_site_tab));
            }
        });
        site_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = site_tv.getWidth();
                select.animate().x(size).setDuration(100);
                site_tv.setTextColor(Color.WHITE);
                site_tv.setBackground(getResources().getDrawable(R.drawable.back_site_select));
                source_tv.setTextColor(def);
                sourceRecyclerView.setVisibility(View.GONE);
                siteRecyclerView.setVisibility(View.VISIBLE);
                setUpSiteObservers();
            }
        });

    }

    private void setUpSiteObservers() {
//
//        tripViewModel.getAllSource().observe(requireActivity(), new Observer<List<SourceInformation>>() {
//            @Override
//            public void onChanged(List<SourceInformation> sourceInformations) {
//                progressBar.setVisibility(View.GONE);
//                sourceSummaryAdapter.setSourceInformationList(sourceInformations);
//                sourceRecyclerView.setAdapter(siteSummaryAdapter);
//                sourceRecyclerView.setLayoutManager(mLayoutManager);
//            }
//        });
        tripViewModel.getAllSite().observe(requireActivity(), new Observer<List<SiteInformation>>() {
            @Override
            public void onChanged(List<SiteInformation> siteInformations) {
                mLayoutManager = new LinearLayoutManager(getActivity());
//        sourceRecyclerView.addItemDecoration(new TripsDecorator(20));
                siteSummaryAdapter = new SiteSummaryAdapter(getActivity(), new ArrayList<SiteInformation>());
                progressBar.setVisibility(View.GONE);
                siteSummaryAdapter.setSiteInformationObjectList(siteInformations);
                siteRecyclerView.setAdapter(siteSummaryAdapter);
                siteRecyclerView.setLayoutManager(mLayoutManager);
            }
        });

    }
    private void setUpSourceObservers() {

        tripViewModel.getAllSource().observe(requireActivity(), new Observer<List<SourceInformation>>() {
            @Override
            public void onChanged(List<SourceInformation> sourceInformations) {
                mLayoutManager = new LinearLayoutManager(getActivity());
                sourceSummaryAdapter = new SourceSummaryAdapter(getActivity(), new ArrayList<SourceInformation>());
                progressBar.setVisibility(View.GONE);
                sourceSummaryAdapter.setSourceInformationList(sourceInformations);
                sourceRecyclerView.setAdapter(sourceSummaryAdapter);
                sourceRecyclerView.setLayoutManager(mLayoutManager);
            }
        });

    }

    private void setUpSlider(View slideView) {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SwipeButton swipeButton = (SwipeButton) slideView.findViewById(R.id.slideView);

        if(sharedPreferences.getInt("selected",0)==1){
            swipeButton.setEnabled(false);
            swipeButton.setHasActivationState(false);
            swipeButton.setText("Trip Selected");
            swipeButton.setDisabledDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_check_circle_24));
        }
        if(sharedPreferences.getInt("selected",0)==2){
            swipeButton.setEnabled(false);
            swipeButton.setText("Trip Completed");
            swipeButton.setDisabledDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_baseline_celebration_24));
        }
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
//                TripClientData tripClientData = new TripClientData(1, tripObjectList.get(0).getTripId());
//                tripViewModel.setInsertTripClient(tripClientData);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("selected", 1);
                editor.apply();
                swipeButton.setEnabled(false);


                String msg = "";
                if (active) {
                    msg = "Your trip has been selected!" + tripViewModel.getGetSelected();
                    tripViewModel.setSelection();
                } else
                    msg = "Your trip has been selected!";
                CustomToast.showToast(getActivity(), msg);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpUI() {
//        sourceSummaryAdapter = new SourceSummaryAdapter(getActivity(), new ArrayList<SourceInformation>());
//        sourceRecyclerView.scrollToPosition(0);

    }

}