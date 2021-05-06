package com.example.git_set_code.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.git_set_code.R;
import com.example.git_set_code.adapters.TripsAdapter;
import com.example.git_set_code.step_view.VerticalStepView;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.TripClientData;
import com.example.git_set_code.utils.CustomToast;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class BottomSheetAdapter extends  RecyclerView.Adapter<BottomSheetAdapter.ViewHolder> {
    List<SiteInformation> siteInformationObjectList;
    List<SourceInformation> sourceInformationObjectList;
    Activity activity;
    int tripTracker;
    public BottomSheetAdapter(
            Activity activity,
            List<Trip> tripObjectList,
            List<SiteInformation> siteInformationObjectList,
            List<SourceInformation> sourceInformationObjectList, ViewModelStoreOwner owner){
        this.siteInformationObjectList = siteInformationObjectList;
        this.sourceInformationObjectList = sourceInformationObjectList;
        this.activity = activity;
        Log.i("Bottom", ""+tripTracker);
    }
    public void setSiteInformationObjectList(List<SiteInformation> siteInformationObjectList) {
        this.siteInformationObjectList = siteInformationObjectList;
    }

    public void setSourceInformationObjectList(List<SourceInformation> sourceInformationObjectList) {
        this.sourceInformationObjectList = sourceInformationObjectList;
    }
    @NonNull
    @Override
    public BottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomSheetAdapter.ViewHolder holder, int position) {
        setUpStepView(holder);
    }
    private void setUpStepView(BottomSheetAdapter.ViewHolder holder) {
        List<String> stepsBeanList = new ArrayList<>();
        for (int i=0; i<sourceInformationObjectList.size(); i++){
            String waypointType="SOURCE";
            stepsBeanList.add(sourceInformationObjectList.get(i).getDestinationName().trim().toUpperCase()+" ("+waypointType+")"+"\n"+sourceInformationObjectList.get(i).getAddress1().trim().toUpperCase()+" "+sourceInformationObjectList.get(i).getCity().trim().toUpperCase()+" "+sourceInformationObjectList.get(i).getStateAbbrev().trim().toUpperCase());
        }
        for (int i=0; i<siteInformationObjectList.size(); i++){
            String waypointType="SITE";
            stepsBeanList.add(siteInformationObjectList.get(i).getDestinationName().trim().toUpperCase()+" ("+waypointType+")"+"\n"+siteInformationObjectList.get(i).getAddress1().trim().toUpperCase()+" "+siteInformationObjectList.get(i).getCity().trim().toUpperCase()+" "+siteInformationObjectList.get(i).getStateAbbrev().trim().toUpperCase());
        }

        SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        tripTracker = sharedPreferences.getInt("tripCounter",0);
        Log.i("bottomAd",""+tripTracker);
        stepsBeanList.add("");
        holder.getStepView().setStepsViewIndicatorComplectingPosition(tripTracker)
                .reverseDraw(false)
                .setStepViewTexts(stepsBeanList)
                .setLinePaddingProportion(2)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(activity, R.color.source_green))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(activity, R.color.site_color))
                .setStepViewUnComplectedTextColor(R.color.source_green)
                .setStepViewComplectedTextColor(ContextCompat.getColor(activity, R.color.option_outline))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(activity,  R.color.option_outline))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(activity, R.drawable.ic_baseline_check_circle_24))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(activity, R.drawable.uncompleted))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(activity, R.drawable.ic_baseline_check_circle_24)).setTextSize(16);
    }
    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final VerticalStepView stepView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepView = (VerticalStepView) itemView.findViewById(R.id.step_view_sheet);
        }
        public VerticalStepView getStepView() {
            return stepView;
        }

    }
}

