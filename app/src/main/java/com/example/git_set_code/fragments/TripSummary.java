package com.example.git_set_code.fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.git_set_code.R;
import com.example.git_set_code.adapters.SiteSummaryAdapter;
import com.example.git_set_code.adapters.SourceSummaryAdapter;
import com.example.git_set_code.networkChecker.CheckNetwork;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.ViewModel.TripViewModel;
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

    List<TripsData> tripsDataList;
    Context thiscontext;
    SiteSummaryAdapter siteSummaryAdapter;
    SourceSummaryAdapter sourceSummaryAdapter;


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
//        sourceRecyclerView = (RecyclerView) rootView.findViewById(R.id.source_list);
        tripsDataList = new ArrayList<>();
        slideView = (SlideView) rootView.findViewById(R.id.slideView_summary);
        progressBar= (ProgressBar)rootView.findViewById(R.id.progress_circular);
        tripViewModel = new ViewModelProvider(requireActivity()).get(TripViewModel.class);
        setUpUI();
        setUpObservers();
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
    private void setUpObservers() {
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
                progressBar.setVisibility(View.GONE);
                siteSummaryAdapter.setSiteInformationObjectList(siteInformations);
                siteRecyclerView.setAdapter(siteSummaryAdapter);
                siteRecyclerView.setLayoutManager(mLayoutManager);
            }
        });

    }

    private void setUpSlider(SlideView slideView) {
        slideView.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideView slideView) {
                // vibrate the device
                Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                if(vibrator.hasVibrator()){vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)); }
                slideView.setText("Selected");
                slideView.setButtonBackgroundColor(ColorStateList.valueOf(Color.LTGRAY));
                slideView.setSlideBackgroundColor(ColorStateList.valueOf(Color.GRAY));
                slideView.setEnabled(false);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpUI() {
        mLayoutManager = new LinearLayoutManager(getActivity());
//
//        sourceRecyclerView.addItemDecoration(new TripsDecorator(20));
//        sourceSummaryAdapter = new SourceSummaryAdapter(getActivity(), new ArrayList<SourceInformation>());
//        sourceRecyclerView.scrollToPosition(0);

        siteRecyclerView.addItemDecoration(new TripsDecorator(20));
        siteSummaryAdapter = new SiteSummaryAdapter(getActivity(), new ArrayList<SiteInformation>());
        siteRecyclerView.scrollToPosition(0);
    }

}