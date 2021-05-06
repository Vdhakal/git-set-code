package com.example.git_set_code.fragments;

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

/**
 * This activity extends the RecyclerView.Adapter and handles the bottom shhet of the adapter
 */
public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder> {
    List<SiteInformation> siteInformationObjectList;
    List<SourceInformation> sourceInformationObjectList;
    Context context;
    int tripTracker;

    /**
     * @param context, a Context object
     * @param tripObjectList, a list of Trip objects
     * @param siteInformationObjectList, a list of siteInformationObject
     * @param sourceInformationObjectList, a list of sourceInformationObject
     * @param owner, a ViewModelStoreOwner object
     * @param tripTracker, an int keeping the track of trips
     */
    public BottomSheetAdapter(
            Context context,
            List<Trip> tripObjectList,
            List<SiteInformation> siteInformationObjectList,
            List<SourceInformation> sourceInformationObjectList, ViewModelStoreOwner owner, int tripTracker) {
        this.siteInformationObjectList = siteInformationObjectList;
        this.sourceInformationObjectList = sourceInformationObjectList;
        this.context = context;
        this.tripTracker = tripTracker;
        Log.i("Bottom", "" + tripTracker);
    }

    /**
     * Setter for siteInformationObjectList
     * @param siteInformationObjectList, a list of siteInformation object
     */
    public void setSiteInformationObjectList(List<SiteInformation> siteInformationObjectList) {
        this.siteInformationObjectList = siteInformationObjectList;
    }


    /**
     * Setter for sourceInformationObjectList
     * @param sourceInformationObjectList, a sourceInformationObjectList object
     */
    public void setSourceInformationObjectList(List<SourceInformation> sourceInformationObjectList) {
        this.sourceInformationObjectList = sourceInformationObjectList;
    }

    /**
     * This method creates a ViewHolder when the activity gets loaded
     *
     * @param parent,   a ViewGroup object
     * @param viewType, an integer value representing the type of the view
     * @return a BottomSheetAdapter.ViewHolder object
     */
    @NonNull
    @Override
    public BottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_layout, parent, false);
        return new ViewHolder(view);
    }


    /**
     * This method updates the RecyclerView.ViewHolder contents with the item at the given position
     *
     * @param holder,   a TripsAdapter.ViewHolder object representing the holder for the trips adapter
     * @param position, an integer value representing the position of the item
     */
    @Override
    public void onBindViewHolder(@NonNull BottomSheetAdapter.ViewHolder holder, int position) {
        setUpStepView(holder);
    }

    /**
     * This method sets up the new step View
     *
     * @param holder, a ViewHolder object
     */
    private void setUpStepView(BottomSheetAdapter.ViewHolder holder) {
        List<String> stepsBeanList = new ArrayList<>();
        for (int i = 0; i < sourceInformationObjectList.size(); i++) {
            String waypointType = "SOURCE";
            stepsBeanList.add(sourceInformationObjectList.get(i).getDestinationName().trim().toUpperCase() + " (" + waypointType + ")" + "\n" + sourceInformationObjectList.get(i).getAddress1().trim().toUpperCase() + " " + sourceInformationObjectList.get(i).getCity().trim().toUpperCase() + " " + sourceInformationObjectList.get(i).getStateAbbrev().trim().toUpperCase());
        }
        for (int i = 0; i < siteInformationObjectList.size(); i++) {
            String waypointType = "SITE";
            stepsBeanList.add(siteInformationObjectList.get(i).getDestinationName().trim().toUpperCase() + " (" + waypointType + ")" + "\n" + siteInformationObjectList.get(i).getAddress1().trim().toUpperCase() + " " + siteInformationObjectList.get(i).getCity().trim().toUpperCase() + " " + siteInformationObjectList.get(i).getStateAbbrev().trim().toUpperCase());
        }
        stepsBeanList.add("");
        holder.getStepView().setStepsViewIndicatorComplectingPosition(stepsBeanList.size() - siteInformationObjectList.size() - sourceInformationObjectList.size() + 1)
                .reverseDraw(false)
                .setStepViewTexts(stepsBeanList)
                .setLinePaddingProportion(1)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(context, R.color.source_green))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(context, R.color.site_color))
                .setStepViewComplectedTextColor(ContextCompat.getColor(context, R.color.option_outline))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(context, R.color.option_outline))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.source_circle))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.site_circle))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_circle_24)).setTextSize(16);
    }

    /**
     * Getter for itemCount
     * @return 1
     */
    @Override
    public int getItemCount() {
        return 1;
    }


    /**
     * The ViewHolder class extends the RecyclerView.ViewHolder and caches views associated with the default preference layouts
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final VerticalStepView stepView;


        /**
         * Constructor for the ViewHolder class
         *
         * @param itemView, a View object
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stepView = (VerticalStepView) itemView.findViewById(R.id.step_view_sheet);
        }

        /**
         * Getter for stepView
         * @return stepView, a VerticalStepView object
         */
        public VerticalStepView getStepView() {
            return stepView;
        }

    }
}

