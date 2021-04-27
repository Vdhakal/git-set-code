package com.example.git_set_code.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.git_set_code.R;
import com.example.git_set_code.cache.TripsObject;
import com.example.git_set_code.step_view.VerticalStepView;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.Truck;
import com.example.git_set_code.utils.CustomToast;
import com.example.git_set_code.viewmodels.TripsData;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {

//    private List<TripsObject> tripsObjects;
    List<Driver> driverObjectList;
    List<SiteInformation> siteInformationObjectList;
    List<SourceInformation> sourceInformationObjectList;
    List<Truck> truckObjectList;
    List<Trailer> trailerObjectList;
    List<Trip> tripObjectList;
    private boolean expanded, state;
    private Context context;
    private Activity activity;

    public void setDriverObjectList(List<Driver> driverObjectList) {
        this.driverObjectList = driverObjectList;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setSiteInformationObjectList(List<SiteInformation> siteInformationObjectList) {
        this.siteInformationObjectList = siteInformationObjectList;
    }

    public void setSourceInformationObjectList(List<SourceInformation> sourceInformationObjectList) {
        this.sourceInformationObjectList = sourceInformationObjectList;
    }

    public void setTruckObjectList(List<Truck> truckObjectList) {
        this.truckObjectList = truckObjectList;
    }

    public void setTrailerObjectList(List<Trailer> trailerObjectList) {
        this.trailerObjectList = trailerObjectList;
    }

    public void setTripObjectList(List<Trip> tripObjectList) {
        this.tripObjectList = tripObjectList;
    }

    public TripsAdapter(Context context,
                        List<Trip> tripObjectList,
                        List<SiteInformation> siteInformationObjectList,
                        List<SourceInformation> sourceInformationObjectList)
    {
        this.tripObjectList = tripObjectList;
        this.siteInformationObjectList = siteInformationObjectList;
        this.sourceInformationObjectList = sourceInformationObjectList;
//        this.tripsObjects = tripsObjects;
        this.expanded = false;
        this.context = context;
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }
    @NonNull
    @Override
    public TripsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsAdapter.ViewHolder holder, int position) {
        holder.getExpandableLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.getTitle().setText("Trip: "+tripObjectList.get(position).getTripName());
        holder.getProductName().setText(siteInformationObjectList.get(position).getProductDesc());
        holder.getStops().setText("3");
        setUpStepView(holder);
        setUpSlider(holder, position);
        onSummaryButtonClick(holder.getSummaryButton());
    }
    //This is how you'd change fragments

    private void onSummaryButtonClick(NeumorphButton sourceButton) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v);
            }
        });
    }
    private void swapFragment(View v){
        Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_tripSummary);

    }
    private void setUpSlider(ViewHolder holder, int position) {
        holder.getSlideView().setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                String msg="";
                if(active)
                    msg = "Trip: "+ tripObjectList.get(position).getTripId() +" has been selected!";
                else
                    msg = "Trip: "+ tripObjectList.get(position).getTripId() +" has been deselected!";
                CustomToast.showToast(activity, msg);
            }
        });
    }

    private void setUpStepView(ViewHolder holder) {
        List<String> stepsBeanList = new ArrayList<>();
        for (int i=0; i<sourceInformationObjectList.size(); i++){
            String waypointType="SOURCE";
            stepsBeanList.add(sourceInformationObjectList.get(i).getDestinationName().trim().toUpperCase()+" ("+waypointType+")"+"\n"+sourceInformationObjectList.get(i).getAddress1().trim().toUpperCase()+" "+sourceInformationObjectList.get(i).getCity().trim().toUpperCase()+" "+sourceInformationObjectList.get(i).getStateAbbrev().trim().toUpperCase());
        }
        for (int i=0; i<siteInformationObjectList.size(); i++){
            String waypointType="SITE";
            stepsBeanList.add(siteInformationObjectList.get(i).getDestinationName().trim().toUpperCase()+" ("+waypointType+")"+"\n"+siteInformationObjectList.get(i).getAddress1().trim().toUpperCase()+" "+siteInformationObjectList.get(i).getCity().trim().toUpperCase()+" "+siteInformationObjectList.get(i).getStateAbbrev().trim().toUpperCase());
        }
        stepsBeanList.add("");
        holder.getStepView().setStepsViewIndicatorComplectingPosition(stepsBeanList.size()-siteInformationObjectList.size()-sourceInformationObjectList.size())
                .reverseDraw(false)
                .setStepViewTexts(stepsBeanList)
                .setLinePaddingProportion(1)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(context, R.color.source_green))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(context, R.color.site_color))
                .setStepViewComplectedTextColor(ContextCompat.getColor(context, R.color.option_outline))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(context,  R.color.option_outline))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.source_circle))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.site_circle))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_circle_24)).setTextSize(16);
    }

    @Override
    public int getItemCount() {
        return tripObjectList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView  productName, stops, title;
        private final NeumorphCardView cardView;
        private final ConstraintLayout expandableLayout;
        private final VerticalStepView stepView;
        private final SwipeButton slideView;
        private final NeumorphButton summaryButton;
        int state;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.trips_card);
            title = itemView.findViewById(R.id.trip_title);
            productName = itemView.findViewById(R.id.tv_product_name);
            stops = itemView.findViewById(R.id.tv_stops);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            stepView = (VerticalStepView) itemView.findViewById(R.id.step_view);
            slideView = (SwipeButton) itemView.findViewById(R.id.slideView);
            summaryButton = (NeumorphButton) itemView.findViewById(R.id.summary_button);
            state=0;
            cardView.setShapeType(state);
            expandOnClick(cardView);
            state=1;
        }

        private void expandOnClick(NeumorphCardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
                notifyItemChanged(getAdapterPosition());
            }
        });
        }

        public NeumorphButton getSummaryButton() {
            return summaryButton;
        }

        public TextView getProductName() { return productName; }

        public TextView getStops() {
            return stops;
        }

        public ConstraintLayout getExpandableLayout(){return expandableLayout;}

        public VerticalStepView getStepView() {
            return stepView;
        }

        public SwipeButton getSlideView() {
            return slideView;
        }

        public TextView getTitle() {
            return title;
        }
    }
}
