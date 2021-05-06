package com.example.git_set_code.adapters;

//Importing necessary packages

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

/**
 * Activity class for the site summary
 */
public class SiteSummaryAdapter extends RecyclerView.Adapter<SiteSummaryAdapter.ViewHolder> {

    private boolean expanded;
    List<SiteInformation> siteInformationObjectList;
    SlidebarStateHolder slidebarStateHolder;
    Context context;
    int step;

    /**
     *Argument Constructor
     * @param context the Context object
     * @param siteInformationObjectList a List of SiteInformation objects
     */
    public SiteSummaryAdapter(Context context, List<SiteInformation> siteInformationObjectList) {
        this.siteInformationObjectList = siteInformationObjectList;
        this.context = context;
    }

    /**
     *Setter for SiteInformationObjectList
     * @param siteInformationObjectList a list of SiteInformation objects
     */
    public void setSiteInformationObjectList(List<SiteInformation> siteInformationObjectList) {
        this.siteInformationObjectList = siteInformationObjectList;
    }


    /**
     * This method creates a new ViewHolder of the site_item layout whenever the RecyclerView needs it.
     * @param parent a ViewGroup object
     * @param viewType an int value
     * @return SiteSummaryAdapter.ViewHolder, a view holder of the site summary adapter
     */
    @NonNull
    @Override
    public SiteSummaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method updates the RecyclerView.ViewHolder contents with the item at the given position
     * @param holder a SiteSummaryAdapter.ViewHolder object
     * @param position the position of the item
     */
    @Override
    public void onBindViewHolder(@NonNull SiteSummaryAdapter.ViewHolder holder, int position) {
        holder.getExpandableSummaryLayout().setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.getWayPointType().setText("Site Container: " + siteInformationObjectList.get(position).getDestinationCod());
        holder.getProductName().setText(siteInformationObjectList.get(position).getProductDesc());
        holder.getVendorName().setText(siteInformationObjectList.get(position).getDestinationName());
        holder.getTerminalName().setText(siteInformationObjectList.get(position).getSiteContainerDescription());
        holder.getTerminalAddress().setText(siteInformationObjectList.get(position).getAddress1().trim() + ", " + siteInformationObjectList.get(position).getCity().trim() + " " + siteInformationObjectList.get(position).getStateAbbrev().trim());
        holder.getSpecialInstructions().setText(siteInformationObjectList.get(position).getFill());
        holder.getQuantities().setText(String.valueOf(siteInformationObjectList.get(position).getRequestedQty()));
        onSourceSummaryButtonClicked(holder.getFormButton());
    }

    /**
     * This method swaps the current fragment with the new view when the button is clicked
     * @param sourceButton the Button object
     */
    private void onSourceSummaryButtonClicked(Button sourceButton) {
        sourceButton.setOnClickListener(new View.OnClickListener() {

            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                swapFragment(v);
            }
        });
    }

    /**
     * This method swaps the fragment
     * @param v, the View object
     */
    private void swapFragment(View v) {
        Navigation.findNavController(v).navigate(R.id.action_tripSummary_to_temporarySite);

    }

    /**
     * Getter for the number of items itemCount
     * @return an int value representing the size of the list of siteInformation objects
     */
    @Override
    public int getItemCount() {
        return siteInformationObjectList.size();
    }

    /**
     * The ViewHolder class caches views associated with the default Preference layouts
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName, vendorName, terminalName, terminalAddress, specialInstructions, quantities, wayPointType;
        private final ConstraintLayout expandable_summary_layout;
        private final NeumorphCardView cardView;
        private final NeumorphButton formButton;

        /**
         * Constructor for the ViewHolder class
         * @param itemView a View object
         */
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
            if (expanded) cardView.setShapeType(1);
            else {
                cardView.setShapeType(0);
                expanded = false;
            }

        }

        /**
         * This method listens to a click and expands the cardView object received as a parameter
         * @param cardView a NeumorphCardView object
         */
        private void expandOnClick(NeumorphCardView cardView) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    cardView.setShapeType(step);
                    Log.i("expand", "" + cardView.getShapeType());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }


        /**
         * Returns the layout defined for the expendable summary
         * @return expandable_summary_layout, a constraint layout of the expendable summary
         */
        public ConstraintLayout getExpandableSummaryLayout() {
            return expandable_summary_layout;
        }

        /**
         * Getter for wayPointType
         * @return wayPointType, a TextView object
         */
        public TextView getWayPointType() {
            return wayPointType;
        }

        /**
         * Getter for productName
         * @return productName, a TextView object
         */
        public TextView getProductName() {
            return productName;
        }

        /**
         * Getter for VendorName
         * @return vendorName, a TextView object
         */
        public TextView getVendorName() {
            return vendorName;
        }

        /**
         * Getter for terminalName
         * @return terminalName, a TextView object
         */
        public TextView getTerminalName() {
            return terminalName;
        }

        /**
         * Getter for terminalAddress
         * @return terminalAddress, a TextView object
         */
        public TextView getTerminalAddress() {
            return terminalAddress;
        }

        /**
         * Getter for formButton
         * @return formButton, a button object
         */
        public Button getFormButton() {
            return formButton;
        }

        /**
         * Getter for specialInstructions
         * @return specialInstructions, a TextView object
         */
        public TextView getSpecialInstructions() {
            return specialInstructions;
        }

        /**
         * Getter for Quantities
         * @return quantities, a TextView object
         */
        public TextView getQuantities() {
            return quantities;
        }

    }
}
