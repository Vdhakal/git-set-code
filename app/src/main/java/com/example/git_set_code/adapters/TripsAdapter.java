package com.example.git_set_code.adapters;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;
import com.example.git_set_code.step_view.VerticalStepView;
import com.example.git_set_code.viewmodels.TripsData;

import java.util.ArrayList;
import java.util.List;

import ng.max.slideview.SlideView;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {

    List<TripsData> tripsDataList;
    boolean expanded;
    Context context;
    public TripsAdapter(Context context, List<TripsData> tripsData){
        this.tripsDataList = tripsData;
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
        holder.getTitle().setText("Trip 1");
        holder.getProductName().setText(tripsDataList.get(1).getProductDesc());
        holder.getStops().setText(String.valueOf(tripsDataList.get(0).getStops()));
        setUpStepView(holder);
        setUpSlider(holder);
    }

    private void setUpSlider(ViewHolder holder) {
        holder.getSlideView().setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideView slideView) {
                // vibrate the device
                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                if(vibrator.hasVibrator()){vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)); }
                holder.getSlideView().setText("Selected");
                holder.getSlideView().setButtonBackgroundColor(ColorStateList.valueOf(Color.LTGRAY));
                holder.getSlideView().setSlideBackgroundColor(ColorStateList.valueOf(Color.GRAY));
                holder.getSlideView().setEnabled(false);

            }
        });
    }

    private void setUpStepView(ViewHolder holder) {
        List<String> stepsBeanList = new ArrayList<>();
        for (int i=0; i<tripsDataList.size(); i++){
            String waypointType="";
            if(tripsDataList.get(i).getWaypointTypeDescription().equals("Site Container"))waypointType="SITE";
            if(tripsDataList.get(i).getWaypointTypeDescription().equals("Source"))waypointType="SOURCE";
            stepsBeanList.add(tripsDataList.get(i).getDestinationName().trim().toUpperCase()+" ("+waypointType+")"+"\n"+tripsDataList.get(i).getAddress1().trim().toUpperCase()+" "+tripsDataList.get(i).getCity().trim().toUpperCase()+" "+tripsDataList.get(i).getStateAbbrev().trim().toUpperCase());
        }
        stepsBeanList.add("");
        holder.getStepView().setStepsViewIndicatorComplectingPosition(stepsBeanList.size()-1)
                .reverseDraw(false)
                .setStepViewTexts(stepsBeanList)
                .setLinePaddingProportion(1)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(context, R.color.source_green))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(context, android.R.color.holo_green_light))
                .setStepViewComplectedTextColor(ContextCompat.getColor(context, R.color.step_indicator))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(context,  R.color.background_orange))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.source_circle))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_circle_24))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.ic_baseline_check_circle_24)).setTextSize(16);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView  productName, stops, title;
        private final CardView cardView;
        private final ConstraintLayout expandableLayout;
        private final VerticalStepView stepView;
        private final SlideView slideView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.trips_card_view);
            title = itemView.findViewById(R.id.trip_title);
            productName = itemView.findViewById(R.id.tv_product_name);
            stops = itemView.findViewById(R.id.tv_stops);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            stepView = (VerticalStepView) itemView.findViewById(R.id.step_view);
            slideView = (SlideView) itemView.findViewById(R.id.slideView);
            expandOnClick(cardView);
        }

        private void expandOnClick(CardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
                notifyItemChanged(getAdapterPosition());
            }
        });
        }

        public TextView getProductName() { return productName; }

        public TextView getStops() {
            return stops;
        }

        public ConstraintLayout getExpandableLayout(){return expandableLayout;}

        public VerticalStepView getStepView() {
            return stepView;
        }

        public SlideView getSlideView() {
            return slideView;
        }

        public TextView getTitle() {
            return title;
        }
    }
}
