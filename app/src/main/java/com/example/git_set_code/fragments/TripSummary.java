package com.example.git_set_code.fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.apiHelpers.TripsAPIService;
import com.example.git_set_code.adapters.TripsSummaryAdapter;
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
    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    private SlideView slideView;

    List<TripsData> tripsDataList;
    Context thiscontext;
    TripsSummaryAdapter adapter;


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
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.trip_summary_list);
        tripsDataList = new ArrayList<>();
        slideView = (SlideView) rootView.findViewById(R.id.slideView_summary);
        extractData();
        setUpSlider(slideView);

        return rootView;
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
    //This is how you'd change fragments

/*    private void onSourceButtonClicked(Button sourceButton) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v);
            }
        });
    }
    private void swapFragment(View v){
        Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_temporarySource);

    }*/

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
                adapter = new TripsSummaryAdapter(getActivity(), tripsDataList);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.scrollToPosition(0);
            }
        });
    }
}