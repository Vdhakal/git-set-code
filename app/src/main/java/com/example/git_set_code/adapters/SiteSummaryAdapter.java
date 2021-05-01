package com.example.git_set_code.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.git_set_code.R;
import com.example.git_set_code.cache.TripsObject;
import com.example.git_set_code.helperClasses.SlidebarStateHolder;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.Truck;


import java.util.List;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.ShapeType;

public class SiteSummaryAdapter extends RecyclerView.Adapter<SiteSummaryAdapter.ViewHolder> {

    private boolean expanded;
    List<SiteInformation> siteInformationObjectList;
    SlidebarStateHolder slidebarStateHolder;
    Context context;
    int step;
    public SiteSummaryAdapter(Context context, List<SiteInformation> siteInformationObjectList){
        this.siteInformationObjectList = siteInformationObjectList;
        this.context = context;
    }

    public void setSiteInformationObjectList(List<SiteInformation> siteInformationObjectList) {
        this.siteInformationObjectList = siteInformationObjectList;
    }


    @NonNull
    @Override
    public SiteSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiteSummaryAdapter.ViewHolder holder, int position) {
        holder.getExpandableSummaryLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.getWayPointType().setText("Site Container: "+siteInformationObjectList.get(position).getSiteContainerDescription());
        holder.getProductName().setText(siteInformationObjectList.get(position).getProductDesc());
        holder.getVendorName().setText(siteInformationObjectList.get(position).getDestinationCod());
        holder.getTerminalName().setText(siteInformationObjectList.get(position).getDestinationName());
        holder.getTerminalAddress().setText(siteInformationObjectList.get(position).getAddress1().trim()+", "+siteInformationObjectList.get(position).getCity().trim()+" "+siteInformationObjectList.get(position).getStateAbbrev().trim());
        holder.getSpecialInstructions().setText(siteInformationObjectList.get(position).getFill());
        holder.getQuantities().setText(String.valueOf(siteInformationObjectList.get(position).getRequestedQty()));
        onSourceSummaryButtonClicked(holder.getFormButton());
    }

    private void onSourceSummaryButtonClicked(Button sourceButton) {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(v);
            }
        });
    }
    private void swapFragment(View v){
        Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySite);

    }
    @Override
    public int getItemCount() {
        return siteInformationObjectList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView productName, vendorName, terminalName, terminalAddress, specialInstructions, quantities, wayPointType;
        private final ConstraintLayout expandable_summary_layout;
        private final NeumorphCardView cardView;
        private final NeumorphButton formButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wayPointType = itemView.findViewById(R.id.trip_summary_title);
            expandable_summary_layout = itemView.findViewById(R.id.expandable_layout_summary);
            cardView = itemView.findViewById(R.id.site_card);
            productName = itemView.findViewById(R.id.tv_product_name);
            vendorName = itemView.findViewById(R.id.tv_vendor_name);
            terminalName = itemView.findViewById(R.id.tv_terminal_name);
            terminalAddress = itemView.findViewById(R.id.tv_terminal_address);

            specialInstructions = itemView.findViewById(R.id.tv_special_instructions);
            quantities = itemView.findViewById(R.id.tv_quantities);
            formButton = itemView.findViewById(R.id.enter_information_site);
            expandOnClick(cardView);
            if(expanded)cardView.setShapeType(1);
            else {cardView.setShapeType(0);
                expanded=false;}

        }

        private void expandOnClick(NeumorphCardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    cardView.setShapeType(step);
                    Log.i("expand",""+cardView.getShapeType());
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
