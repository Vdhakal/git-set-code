package com.example.git_set_code.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.git_set_code.R;
import com.example.git_set_code.adapters.TripsAdapter;
import com.example.git_set_code.adapters.TripsAdapterOnline;
import com.example.git_set_code.apiHelpers.TripsAPIService;
import com.example.git_set_code.adapters.TripsSummaryAdapter;
import com.example.git_set_code.cache.TripsObject;
import com.example.git_set_code.networkChecker.CheckNetwork;
import com.example.git_set_code.networkChecker.Variables;
import com.example.git_set_code.viewmodels.TripsData;
import com.example.git_set_code.utils.TripsDecorator;
import com.example.git_set_code.viewmodels.TripsObjectViewModel;

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
    private TripsObjectViewModel tripsObjectViewModel;
    private boolean completed = false;
    List<TripsData> tripsDataList;
    Context thiscontext;
    TripsAdapter adapter;
    TripsAdapterOnline onlineAdapter;

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
        tripsObjectViewModel = new ViewModelProvider(requireActivity()).get(TripsObjectViewModel.class);
        CheckNetwork.checkNetworkInfo(thiscontext, new CheckNetwork.OnConnectionStatusChange() {
            @Override
            public void onChange(boolean type) {
                if(type){
                    Toast.makeText(thiscontext, "Connection Available", Toast.LENGTH_SHORT).show();
                    extractData();
                }else {
                    Toast.makeText(thiscontext, "NO Connection", Toast.LENGTH_SHORT).show();
                    setUpUI();
                }
            }
        });

        return rootView;
    }

    private void setUpUI() {
        tripsObjectViewModel.getGetAlltripObjects().observe(requireActivity(), new Observer<List<TripsObject>>() {
            @Override
            public void onChanged(List<TripsObject> tripsObjects) {
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.addItemDecoration(new TripsDecorator(20));
                adapter = new TripsAdapter(getActivity(), tripsObjects);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.scrollToPosition(0);
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void extractData(){
        TripsAPIService tripsAPIService = new TripsAPIService();
        tripsAPIService.getRequestedJson(tripsObjectViewModel, thiscontext, tripsDataList, new TripsAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(thiscontext, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse() {
                progressBar.setVisibility(View.GONE);
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.addItemDecoration(new TripsDecorator(20));
                onlineAdapter = new TripsAdapterOnline(getActivity(), tripsDataList);
                mRecyclerView.setAdapter(onlineAdapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.scrollToPosition(0);

                for (int i = 0; i < tripsDataList.size(); i++) {
                    tripsObjectViewModel.insert(new TripsObject(tripsDataList.get(i)), thiscontext);
                }
                completed = true;
            }

        });
    }
}