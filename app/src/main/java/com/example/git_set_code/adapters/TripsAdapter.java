package com.example.git_set_code.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.getProductName().setText(tripsDataList.get(1).getProductDesc());
        holder.getStops().setText(String.valueOf(tripsDataList.get(0).getStops()));

        List<String> stepsBeanList = new ArrayList<>();
        for (int i=0; i<tripsDataList.size(); i++){
            stepsBeanList.add(tripsDataList.get(i).getDestinationName()+"\n"+tripsDataList.get(i).getAddress1()+" "+tripsDataList.get(i).getCity().trim()+" "+tripsDataList.get(i).getStateAbbrev());
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
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.source_circle))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.source_circle)).setTextSize(16);

        holder.getExpandableLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView  productName, stops;
        private final CardView cardView;
        private final ConstraintLayout expandableLayout;
        private final VerticalStepView stepView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.trips_card_view);
            productName = itemView.findViewById(R.id.tv_product_name);
            stops = itemView.findViewById(R.id.tv_stops);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            stepView = (VerticalStepView) itemView.findViewById(R.id.step_view);

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

    }
}
