package com.example.git_set_code.adapters;

//Importing necessary packages

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.git_set_code.R;
import com.example.git_set_code.cache.TripsObject;
import com.example.git_set_code.fragments.HomeFragment;
import com.example.git_set_code.step_view.VerticalStepView;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.TripClientData;
import com.example.git_set_code.trip_database.Table.Truck;
import com.example.git_set_code.trip_database.ViewModel.TripViewModel;
import com.example.git_set_code.utils.CustomToast;
import com.example.git_set_code.viewmodels.TripsData;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

/**
 * This activity class is for displaying the trips
 */
public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {

    //    private List<TripsObject> tripsObjects;
    List<Driver> driverObjectList;
    List<SiteInformation> siteInformationObjectList;
    List<SourceInformation> sourceInformationObjectList;
    List<Truck> truckObjectList;
    List<Trailer> trailerObjectList;
    List<Trip> tripObjectList;
    TripViewModel tripViewModel;
    private boolean expanded, state;
    private Context context;
    private Activity activity;
    ViewModelStoreOwner owner;
    List<TripClientData> tripClientData;
    public expandState expandState;

    /**
     * This method listens to items when clicked
     *
     * @param expandState, a TripsAdapter.expandState object
     */
    public void setOnItemClickListener(TripsAdapter.expandState expandState) {
        this.expandState = expandState;
    }

    /**
     * This is an Interface for expandState which is implemented by classes to expand the state acording to their needs
     */
    public interface expandState {
        public void cardView(boolean expand);//pass your object types.
    }

    /**
     * Setter for driverObjectList
     *
     * @param driverObjectList, a list of Driver objects
     */
    public void setDriverObjectList(List<Driver> driverObjectList) {
        this.driverObjectList = driverObjectList;
    }

    /**
     * Setter for Activity
     *
     * @param activity, an Activity object
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Setter for tripModelView
     *
     * @param viewModel, a TripViewModel object
     */
    public void setTripViewModel(TripViewModel viewModel) {
        this.tripViewModel = tripViewModel;
    }

    /**
     * Setter for siteInformationObjectList
     *
     * @param siteInformationObjectList, a list of SiteInformation objects
     */
    public void setSiteInformationObjectList(List<SiteInformation> siteInformationObjectList) {
        this.siteInformationObjectList = siteInformationObjectList;
    }

    /**
     * Setter for sourceInformationObjectList
     *
     * @param sourceInformationObjectList, a list of SourceInformation objects
     */
    public void setSourceInformationObjectList(List<SourceInformation> sourceInformationObjectList) {
        this.sourceInformationObjectList = sourceInformationObjectList;
    }

    /**
     * Setter for truckObjectList
     *
     * @param truckObjectList, a list of Truck objects
     */
    public void setTruckObjectList(List<Truck> truckObjectList) {
        this.truckObjectList = truckObjectList;
    }

    /**
     * Setter for trailerObjectList
     *
     * @param trailerObjectList, a list of Trailer objects
     */
    public void setTrailerObjectList(List<Trailer> trailerObjectList) {
        this.trailerObjectList = trailerObjectList;
    }

    /**
     * Setter for tripObjectList
     *
     * @param tripObjectList, a list of Trip objects
     */
    public void setTripObjectList(List<Trip> tripObjectList) {
        this.tripObjectList = tripObjectList;
    }

    /**
     * Setter for TripClientData
     *
     * @param tripClientData, a list of TripClientData
     */
    public void setTripClientData(List<TripClientData> tripClientData) {
        this.tripClientData = tripClientData;
    }

    /**
     * Constructor for TripsAdapter
     *
     * @param context,                    a Context object
     * @param tripObjectList,             a List of tripObjects
     * @param siteInformationObjectList   a list of SiteInformation objects
     * @param sourceInformationObjectList a list of sourceInformation objects
     * @param owner,                      a ViewModelStoreOwner object
     */
    public TripsAdapter(Context context,
                        List<Trip> tripObjectList,
                        List<SiteInformation> siteInformationObjectList,
                        List<SourceInformation> sourceInformationObjectList, ViewModelStoreOwner owner) {
        this.tripObjectList = tripObjectList;
        this.siteInformationObjectList = siteInformationObjectList;
        this.sourceInformationObjectList = sourceInformationObjectList;
//        this.tripsObjects = tripsObjects;
        this.expanded = false;
        this.context = context;
        this.tripViewModel = new ViewModelProvider(owner).get(TripViewModel.class);
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }

    /**
     * This method creates a ViewHolder when the activity gets loaded
     *
     * @param parent,   a ViewGroup object
     * @param viewType, an integer value representing the type of the view
     * @return a TripsAdapter.ViewHolder object
     */
    @NonNull
    @Override
    public TripsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_list, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method updates the RecyclerView.ViewHolder contents with the item at the given position
     *
     * @param holder,   a TripsAdapter.ViewHolder object representing the holder for the trips adapter
     * @param position, an integer value representing the position of the item
     */
    @Override
    public void onBindViewHolder(@NonNull TripsAdapter.ViewHolder holder, int position) {
        holder.getExpandableLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
//        if(expanded){
//            HomeFragment homeFragment = new HomeFragment();
//            homeFragment.setUpSlider( tripObjectList, activity);
//        }
        holder.getTitle().setText("Trip: " + tripObjectList.get(position).getTripName());
        holder.getProductName().setText(sourceInformationObjectList.size() + "");
        holder.getStops().setText(siteInformationObjectList.size() + "");
        setUpStepView(holder);
        onSummaryButtonClick(holder.getSummaryButton());
    }
    //This is how you'd change fragments

    /**
     * This method displays a new view when the summary button is clicked
     *
     * @param sourceButton, a NeumorphButton object
     */
    private void onSummaryButtonClick(NeumorphButton sourceButton) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v);
            }
        });
    }

    /**
     * This method swaps the current fragment with the new View object
     *
     * @param v, the View object
     */
    private void swapFragment(View v) {
        Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_tripSummary);
    }


    /**
     * This method sets up the new step View
     *
     * @param holder, a ViewHolder object
     */
    private void setUpStepView(ViewHolder holder) {
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
     * Getter for item count
     *
     * @return an int representing the size of the list of trip objects
     */
    @Override
    public int getItemCount() {
        return tripObjectList.size();
    }


    /**
     * The ViewHolder class extends the RecyclerView.ViewHolder and caches views associated with the default preference layouts
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName, stops, title;
        private final NeumorphCardView cardView;
        private final ConstraintLayout expandableLayout;
        private final VerticalStepView stepView;
        private final NeumorphButton summaryButton;
        int state;
        SharedPreferences sharedPreferences;
        SwipeButton swipeButton;

        /**
         * Constructor for the ViewHolder class
         *
         * @param itemView, a View object
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.trips_card);
            title = itemView.findViewById(R.id.trip_title);
            productName = itemView.findViewById(R.id.tv_product_name);
            stops = itemView.findViewById(R.id.tv_stops);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            stepView = (VerticalStepView) itemView.findViewById(R.id.step_view);
            summaryButton = (NeumorphButton) itemView.findViewById(R.id.summary_button);
            cardView.setShapeType(state);
            expandOnClick(cardView);
            swipeButton = (SwipeButton) itemView.findViewById(R.id.slideView);

            sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
            if (sharedPreferences.getInt("selected", 0) == 1) {
                swipeButton.setEnabled(false);
                swipeButton.setText("Trip Selected");
                swipeButton.setDisabledDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_circle_24));
            }
            if (sharedPreferences.getInt("selected", 0) == 2) {
                swipeButton.setEnabled(false);
                swipeButton.setText("Trip Completed");
                swipeButton.setDisabledDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_celebration_24));
            }

            if (expanded) cardView.setShapeType(1);
            else {
                cardView.setShapeType(0);
                expanded = false;
            }
        }

        /**
         * This method listens to the click and expands the new cardView
         *
         * @param cardView, a NeumorphCardView object
         */
        private void expandOnClick(NeumorphCardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {

                /**
                 *This method listens to the click and expands the new cardView
                 * @param v, the View object
                 */
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    swipeButton.setOnStateChangeListener(new OnStateChangeListener() {

                        /**
                         *This method displays message when the state is changed
                         * @param active a boolean value
                         */
                        @Override
                        public void onStateChange(boolean active) {
                            TripClientData tripClientData = new TripClientData(1, tripObjectList.get(0).getTripId());
                            tripViewModel.setInsertTripClient(tripClientData);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("selected", 1);
                            editor.apply();
                            swipeButton.setEnabled(false);


                            String msg = "";
                            if (active) {
                                msg = "Your trip has been selected!";
                                tripViewModel.setSelection();
                            } else
                                msg = "Your trip has been deselected!";
                            CustomToast.showToast(activity, msg);
                        }
                    });

                    notifyItemChanged(getAdapterPosition());
                }

            });
        }


        /**
         * Getter for summaryButton
         *
         * @return summaryButton, a NeumorphButton object
         */
        public NeumorphButton getSummaryButton() {
            return summaryButton;
        }

        /**
         * Getter for productName
         *
         * @return productName, the TextView object
         */
        public TextView getProductName() {
            return productName;
        }

        /**
         * Getter for the stops
         *
         * @return stops, a TextView object
         */
        public TextView getStops() {
            return stops;
        }

        /**
         * Getter for the expandableLayout
         *
         * @return expandableLayout, a ConstraintLayout object
         */
        public ConstraintLayout getExpandableLayout() {
            return expandableLayout;
        }

        /**
         * Getter for the stepView
         *
         * @return stepView, a VerticalStepView object
         */
        public VerticalStepView getStepView() {
            return stepView;
        }


        /**
         * Getter for the title
         *
         * @return title, a TextView object
         */
        public TextView getTitle() {
            return title;
        }
    }
}
