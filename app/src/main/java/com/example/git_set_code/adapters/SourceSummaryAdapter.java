package com.example.git_set_code.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;
import com.example.git_set_code.trip_database.Table.SourceInformation;

import java.util.List;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;

public class SourceSummaryAdapter extends RecyclerView.Adapter<SourceSummaryAdapter.ViewHolder> {

    private boolean expanded;
    List<SourceInformation> sourceInformationList;
    Activity activity;
    private SharedPreferences sharedPreferences;

    public SourceSummaryAdapter(Activity activity, List<SourceInformation> sourceInformationList){
        this.sourceInformationList = sourceInformationList;
        this.activity = activity;
        //Toast.makeText(context, tripsDataList.get(2).toString(), Toast.LENGTH_SHORT).show();

    }

    public void setSourceInformationList(List<SourceInformation> sourceInformationList) {
        this.sourceInformationList = sourceInformationList;
    }


    @NonNull
    @Override
    public SourceSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceSummaryAdapter.ViewHolder holder, int position) {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        if(sharedPreferences.getInt("tripTracker",-1)>=position || sharedPreferences.getBoolean("sourceCompleted",false)) {
            holder.getCardView().setBackgroundColor(Color.LTGRAY);
            holder.getTickMark().setVisibility(View.VISIBLE);
        }
        holder.getExpandableSummaryLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.getWayPointType().setText("Source: "+sourceInformationList.get(position).getDestinationCod());
        holder.getVendorName().setText(sourceInformationList.get(position).getDestinationCod());
        holder.getTerminalName().setText(sourceInformationList.get(position).getDestinationName());
        holder.getTerminalAddress().setText(sourceInformationList.get(position).getAddress1().trim()+", "+sourceInformationList.get(position).getCity().trim()+" "+sourceInformationList.get(position).getStateAbbrev().trim());
        onSourceSummaryButtonClicked(holder.getFormButton(),position);
    }

    private void onSourceSummaryButtonClicked(NeumorphButton sourceButton, int position) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v, position);
            }
        });
    }
    private void swapFragment(View v,int position){
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySource, bundle);

    }
    @Override
    public int getItemCount() {
        return sourceInformationList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView  vendorName, terminalName, terminalAddress, wayPointType;
        private final ConstraintLayout expandable_summary_layout;
        private final NeumorphCardView cardView;
        private final NeumorphButton formButton;
        private final ImageView tickMark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wayPointType = itemView.findViewById(R.id.trip_summary_title);
            expandable_summary_layout = itemView.findViewById(R.id.expandable_layout_summary);
            cardView = itemView.findViewById(R.id.source_card);
            vendorName = itemView.findViewById(R.id.tv_vendor_name);
            terminalName = itemView.findViewById(R.id.tv_terminal_name);
            terminalAddress = itemView.findViewById(R.id.tv_terminal_address);
            formButton = itemView.findViewById(R.id.enter_information_source);
            tickMark = itemView.findViewById(R.id.completed_source);
            expandOnClick(cardView);
            if(expanded)cardView.setShapeType(1);
            else cardView.setShapeType(0);

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

        public NeumorphCardView getCardView() {
            return cardView;
        }

        public ImageView getTickMark() {
            return tickMark;
        }
        public ConstraintLayout getExpandableSummaryLayout() {
            return expandable_summary_layout;
        }

        public TextView getWayPointType() {
            return wayPointType;
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

        public NeumorphButton getFormButton() {
            return formButton;
        }



    }
}
