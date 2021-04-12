package com.example.git_set_code.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;
import com.example.git_set_code.helperClasses.SlidebarStateHolder;
import com.example.git_set_code.viewmodels.TripsData;


import java.util.List;

import ng.max.slideview.SlideView;

public class TripsSummaryAdapter extends RecyclerView.Adapter<TripsSummaryAdapter.ViewHolder> {

    List<TripsData> tripsDataList;
    private boolean expanded;
    SlidebarStateHolder slidebarStateHolder;
    Context context;
    public TripsSummaryAdapter(Context context, List<TripsData> tripsData){
        this.tripsDataList = tripsData;
        this.context = context;
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }
    @NonNull
    @Override
    public TripsSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsSummaryAdapter.ViewHolder holder, int position) {
        holder.getExpandableSummaryLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.getWayPointType().setText(tripsDataList.get(position).getWaypointTypeDescription()+" "+position);
        holder.getProductName().setText(tripsDataList.get(position).getProductDesc());
        holder.getVendorName().setText(tripsDataList.get(position).getDestinationCod());
        holder.getTerminalName().setText(tripsDataList.get(position).getDestinationName());
        holder.getTerminalAddress().setText(tripsDataList.get(position).getAddress1().trim()+", "+tripsDataList.get(position).getCity().trim()+" "+tripsDataList.get(position).getStateAbbrev().trim());
        holder.getSpecialInstructions().setText("NONE");
        holder.getQuantities().setText(String.valueOf(tripsDataList.get(position).getRequestedQty()));
        onSourceSummaryButtonClicked(holder.getFormButton(),tripsDataList.get(position).getWaypointTypeDescription());
    }

    private void onSourceSummaryButtonClicked(Button sourceButton, String wayPointType) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v, wayPointType);
            }
        });
    }
    private void swapFragment(View v, String wayPointType){
        if(wayPointType.equals("Source"))Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySource);
        if(wayPointType.trim().equals("Site Container"))Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySite);

    }
    @Override
    public int getItemCount() {
        return tripsDataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView productName, vendorName, terminalName, terminalAddress, specialInstructions, quantities, wayPointType;
        private final ConstraintLayout expandable_summary_layout;
        private final CardView cardView;
        private final Button formButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wayPointType = itemView.findViewById(R.id.trip_summary_title);
            expandable_summary_layout = itemView.findViewById(R.id.expandable_layout_summary);
            cardView = itemView.findViewById(R.id.trip_summary_card_view);
            productName = itemView.findViewById(R.id.tv_product_name);
            vendorName = itemView.findViewById(R.id.tv_vendor_name);
            terminalName = itemView.findViewById(R.id.tv_terminal_name);
            terminalAddress = itemView.findViewById(R.id.tv_terminal_address);

            specialInstructions = itemView.findViewById(R.id.tv_special_instructions);
            quantities = itemView.findViewById(R.id.tv_quantities);
            formButton = itemView.findViewById(R.id.enter_information);
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

        public ConstraintLayout getExpandableSummaryLayout() {
            return expandable_summary_layout;
        }

        public TextView getWayPointType() {
            return wayPointType;
        }

        public TextView getProductName() {
            return productName;
        }
        public TextView getVendorName() {
            return vendorName;
        }

        public TextView getTerminalName() {
            return terminalName;
        }

        public TextView getTerminalAddress() {
            return terminalAddress;
        }

        public Button getFormButton() {
            return formButton;
        }

        public TextView getSpecialInstructions() {
            return specialInstructions;
        }

        public TextView getQuantities() {
            return quantities;
        }



    }
}
